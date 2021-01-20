package com.orgella.auctionsmanagement.application.rest

import com.orgella.auctionsmanagement.application.mapper.AuctionsMapper
import com.orgella.auctionsmanagement.application.request.CreateNewAuctionRequest
import com.orgella.auctionsmanagement.application.response.GetAuctionResponse
import com.orgella.auctionsmanagement.domain.AuctionEntity
import com.orgella.auctionsmanagement.domain.service.AuctionService
import com.orgella.auctionsmanagement.infrastructure.configuration.security.UserInfo
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

    @PreAuthorize("hasRole('ROLE_SELLER')")
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
    ): List<GetAuctionResponse> {
        var auctions: List<AuctionEntity> = emptyList()
        if (category == null) {
            auctions = auctionService.findAllContainingQuery(query)
        } else {
            auctions = auctionService.findAllContainingQueryAndCategory(query, category)
        }

        return auctions.stream()
            .map {
                AuctionsMapper.toResponse(it)
            }.collect(Collectors.toList())
    }

    @PreAuthorize("#username == authentication.principal.username")
    @GetMapping(value = ["/{username}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUserDetailsByUsername(@PathVariable username: String): ResponseEntity<String>? {
        return ResponseEntity.ok("Has access")
    }
}