package com.orgella.auctionsmanagement.application.response

import java.util.*

data class GetReviewResponse(
    val date: Date,
    val reviewerUsername: String,
    val rating: Int,
    val description: String
)
