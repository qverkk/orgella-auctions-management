package com.orgella.auctionsmanagement.application.response

import org.bson.types.Binary
import java.math.BigDecimal

data class GetAuctionResponse(
    val title: String,
    val auctionPath: String,
    val sellerName: String,
    val quantity: Int,
    val boughtQuantity: Int,
    val price: BigDecimal,
    var category: String,
    val reviews: List<GetReviewResponse>,
    val thumbnail: Binary,
    val description: String
)
