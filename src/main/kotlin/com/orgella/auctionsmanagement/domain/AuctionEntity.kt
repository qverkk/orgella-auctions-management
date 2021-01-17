package com.orgella.auctionsmanagement.domain

import org.bson.types.Binary
import java.math.BigDecimal
import java.util.*

data class AuctionEntity(
    var id: UUID,
    val title: String,
    var auctionPath: String,
    var sellerId: UUID,
    var quantity: Int,
    var boughtQuantity: Int,
    var prive: BigDecimal,
    var reviews: MutableList<AuctionReviewsEntity>,
    var thumbnail: Binary,
    var description: String
)
