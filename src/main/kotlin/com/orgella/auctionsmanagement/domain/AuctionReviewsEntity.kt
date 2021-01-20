package com.orgella.auctionsmanagement.domain

import java.util.*

data class AuctionReviewsEntity(
    var id: UUID,
    var date: Date,
    var reviewerUsername: String,
    var rating: Int,
    var description: String
)
