package com.orgella.auctionsmanagement.exceptions.handlers

data class ConstraintViolationError(
    val cause: String,
    val field: String
)
