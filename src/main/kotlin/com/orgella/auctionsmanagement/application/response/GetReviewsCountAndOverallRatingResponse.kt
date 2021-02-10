package com.orgella.auctionsmanagement.application.response

import java.math.BigDecimal

data class GetReviewsCountAndOverallRatingResponse(
    val count: Int,
    val rating: BigDecimal
)
