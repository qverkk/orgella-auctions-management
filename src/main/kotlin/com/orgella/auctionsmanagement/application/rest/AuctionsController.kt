package com.orgella.auctionsmanagement.application.rest

import com.orgella.auctionsmanagement.application.request.CreateNewAuctionRequest
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.security.Principal
import javax.validation.Valid

@RestController
@RequestMapping("auctions")
class AuctionsController {

    @PreAuthorize("hasRole('ROLE_SELLER')")
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createAuction(
        @RequestParam("details") @Valid createNewAuctionRequest: CreateNewAuctionRequest,
        @RequestParam("file") file: MultipartFile,
        principal: Principal
    ) {

    }
}