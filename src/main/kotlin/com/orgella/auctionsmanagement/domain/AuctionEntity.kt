package com.orgella.auctionsmanagement.domain

import org.bson.types.Binary
import java.math.BigDecimal
import java.util.*

data class AuctionEntity(
    var id: UUID,
    val title: String,
    var auctionPath: String,
    var sellerUsername: String,
    var quantity: Int,
    var boughtQuantity: Int,
    var price: BigDecimal,
    var category: String,
    var reviews: MutableList<AuctionReviewsEntity>,
    var thumbnail: Binary,
    var description: String
)
