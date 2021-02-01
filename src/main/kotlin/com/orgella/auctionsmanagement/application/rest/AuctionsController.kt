package com.orgella.auctionsmanagement.application.rest

import com.orgella.auctionsmanagement.application.mapper.AuctionsMapper
import com.orgella.auctionsmanagement.application.request.CreateNewAuctionRequest
import com.orgella.auctionsmanagement.application.response.BasketItemInfo
import com.orgella.auctionsmanagement.application.response.GetAuctionDetailsResponse
import com.orgella.auctionsmanagement.application.response.GetAuctionResponse
import com.orgella.auctionsmanagement.application.response.GetAuctionsForBasketResponse
import com.orgella.auctionsmanagement.domain.AuctionEntity
import com.orgella.auctionsmanagement.domain.service.AuctionService
import com.orgella.auctionsmanagement.exceptions.NoAuctionPath
import com.orgella.auctionsmanagement.infrastructure.configuration.security.UserInfo
import org.bson.internal.Base64
import org.bson.types.Binary
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import java.time.Instant
import java.util.*
import java.util.stream.Collectors
import javax.validation.Valid

@RestController
@RequestMapping("auctions")
class AuctionsController(
    private val auctionService: AuctionService
) {

    //    @PreAuthorize("hasRole('ROLE_SELLER')")
    @PostMapping
    fun createAuction(
        @ModelAttribute @Valid createNewAuctionRequest: CreateNewAuctionRequest,
        auth: Authentication
    ) {
        val auctionPath = createNewAuctionRequest.title.replace(" ", "-").plus("-").plus(Instant.now().epochSecond)
        val userInfo = (auth.principal as UserInfo)

        val auction = AuctionEntity(
            UUID.randomUUID(),
            createNewAuctionRequest.title,
            auctionPath,
            userInfo.username,
            createNewAuctionRequest.quantity,
            0,
            BigDecimal(createNewAuctionRequest.price),
            createNewAuctionRequest.category,
            mutableListOf(),
            Binary(createNewAuctionRequest.file?.bytes),
            createNewAuctionRequest.description
        )

        auctionService.createAuction(auction)
    }

    @GetMapping("/find")
    fun findAuctionsByName(
        @RequestParam query: String,
        @RequestParam(required = false) category: String?
    ): ResponseEntity<List<GetAuctionResponse>> {
        val auctions: List<AuctionEntity> = if (category == null || category == "Wszystkie") {
            auctionService.findAllContainingQuery(query)
        } else {
            auctionService.findAllContainingQueryAndCategory(query, category)
        }

        return ResponseEntity.ok(
            auctions.stream()
                .map {
                    AuctionsMapper.toGetAuctionResponse(it)
                }.collect(Collectors.toList())
        )
    }

    @GetMapping("/details/{auctionPath}")
    fun getAuctionDetails(@PathVariable auctionPath: String): ResponseEntity<GetAuctionDetailsResponse> {
        val auction = auctionService.findByAuctionPath(auctionPath).orElseThrow {
            throw NoAuctionPath("$auctionPath doesn't exist")
        }

        return ResponseEntity.ok(
            AuctionsMapper.toGetAuctionDetailsResponse(auction)
        )
    }

    @GetMapping("/details/basket")
    fun getAutctionsForBasket(@RequestParam auctionPaths: List<String>): ResponseEntity<GetAuctionsForBasketResponse> {
        val auctions = auctionService.findByAuctionPaths(auctionPaths)

        return ResponseEntity.ok(
            GetAuctionsForBasketResponse(
                auctions.stream().map {
                    BasketItemInfo(
                        it.id,
                        it.title,
                        it.auctionPath,
                        it.quantity,
                        it.price,
                        Base64.encode(it.thumbnail.data)
                    )
                }.collect(Collectors.toList())
            )
        )
    }

    @PreAuthorize("#username == authentication.principal.username")
    @GetMapping(value = ["/{username}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUserDetailsByUsername(@PathVariable username: String): ResponseEntity<String>? {
        return ResponseEntity.ok("Has access")
    }
}