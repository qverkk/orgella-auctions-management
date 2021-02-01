package com.orgella.auctionsmanagement.application.response

import java.math.BigDecimal
import java.util.*

data class GetAuctionsForBasketResponse(
    val items: List<BasketItemInfo>
)

data class BasketItemInfo(
    var id: UUID,
    val title: String,
    var auctionPath: String,
    var quantity: Int,
    var price: BigDecimal,
    var thumbnail: String
)
