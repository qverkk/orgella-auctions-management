package com.orgella.auctionsmanagement.application.response

import java.util.*

data class GetReviewsForAuctionResponse(
    val page: Int,
    val maxPages: Int,
    val items: List<ReviewForAuction>
)

data class ReviewForAuction(
    val date: Date,
    val reviewerUsername: String,
    val rating: Int,
    val description: String
)
