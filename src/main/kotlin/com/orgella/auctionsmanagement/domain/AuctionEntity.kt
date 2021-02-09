package com.orgella.auctionsmanagement.domain

import org.bson.types.Binary
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import java.math.BigDecimal
import java.util.*

data class AuctionEntity(
    @field:Id
    var id: UUID,
    val title: String,
    var auctionPath: String,
    var sellerUsername: String,
    var quantity: Int,
    var boughtQuantity: Int,
    var price: BigDecimal,
    var category: String,
    var thumbnail: Binary,
    var description: String,
    @field:Version
    val version: Int
)
