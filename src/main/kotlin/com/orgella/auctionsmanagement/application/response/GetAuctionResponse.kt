package com.orgella.auctionsmanagement.application.response

import java.math.BigDecimal

data class GetAuctionResponse(
    val page: Int,
    val maxPages: Int,
    val items: List<GetAuctionItemResponse>
)

data class GetAuctionItemResponse(
    val title: String,
    val auctionPath: String,
    val boughtQuantity: Int,
    val price: BigDecimal,
    val thumbnail: String
)
