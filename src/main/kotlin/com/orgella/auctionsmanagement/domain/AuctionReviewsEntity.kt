package com.orgella.auctionsmanagement.domain

import java.util.*

data class AuctionReviewsEntity(
    var id: UUID,
    var date: Date,
    var reviewUserId: UUID,
    var rating: Int,
    var description: String
)
