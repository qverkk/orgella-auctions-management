package com.orgella.auctionsmanagement.application.request

import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class CreateNewAuctionRequest(
    @field:NotNull(message = "Quantity cannot be null")
    @field:Size(min = 1, message = "Minimum quantity is 1")
    var quantity: Int,
    @field:NotNull(message = "Quantity cannot be null")
    @field:Pattern(regexp = "^(0|[1-9]\\d+),(\\d){2}$", message = "Price must be in format: 11,1 or 0,1")
    var prive: String,
    @field:NotNull(message = "Description cannot be null")
    @field:Size(min = 30, message = "Minimum description length is 30 characters")
    var description: String
)
