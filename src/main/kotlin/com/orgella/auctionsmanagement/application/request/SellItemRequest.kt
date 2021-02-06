package com.orgella.auctionsmanagement.application.request

data class SellItemRequest(
    val auctionPath: String,
    val quantity: Int
)
