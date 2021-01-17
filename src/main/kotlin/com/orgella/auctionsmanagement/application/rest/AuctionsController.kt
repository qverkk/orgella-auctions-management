package com.orgella.auctionsmanagement.application.rest

import com.orgella.auctionsmanagement.application.request.CreateNewAuctionRequest
import com.orgella.auctionsmanagement.domain.AuctionEntity
import com.orgella.auctionsmanagement.domain.service.AuctionService
import org.bson.types.Binary
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.math.BigDecimal
import java.security.Principal
import java.time.Instant
import java.util.*
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
        principal: Principal
    ) {
        val auctionPath = createNewAuctionRequest.title.replace(" ", "-").plus("-").plus(Instant.now().epochSecond)

        val auction = AuctionEntity(
            UUID.randomUUID(),
            createNewAuctionRequest.title,
            auctionPath,
            UUID.fromString(principal.name),
            createNewAuctionRequest.quantity,
            0,
            BigDecimal(createNewAuctionRequest.prive),
            mutableListOf(),
            Binary(createNewAuctionRequest.file?.bytes),
            createNewAuctionRequest.description
        )

        auctionService.createAuction(auction)
    }
}