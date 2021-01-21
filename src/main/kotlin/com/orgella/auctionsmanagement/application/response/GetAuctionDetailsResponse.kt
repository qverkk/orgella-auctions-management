package com.orgella.auctionsmanagement.application.response

import java.math.BigDecimal

data class GetAuctionDetailsResponse(
    val title: String,
    val auctionPath: String,
    val sellerName: String,
    val quantity: Int,
    val boughtQuantity: Int,
    val price: BigDecimal,
    val reviews: List<GetReviewResponse>,
    val thumbnail: String,
    val description: String
)
