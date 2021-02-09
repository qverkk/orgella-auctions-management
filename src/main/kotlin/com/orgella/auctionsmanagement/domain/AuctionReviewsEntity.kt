package com.orgella.auctionsmanagement.domain

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import java.util.*

data class AuctionReviewsEntity(
    @field:Id
    var id: UUID,
    var date: Date,
    val auctionPath: String,
    var orderId: String,
    var reviewerUsername: String,
    var rating: Int,
    var description: String,
    @field:Version
    val version: Int
)
