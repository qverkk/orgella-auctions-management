package com.orgella.auctionsmanagement.application.mapper

import com.orgella.auctionsmanagement.application.response.GetReviewResponse
import com.orgella.auctionsmanagement.domain.AuctionReviewsEntity

object ReviewsMapper {
    fun toResponse(review: AuctionReviewsEntity): GetReviewResponse {
        return GetReviewResponse(
            review.date,
            review.reviewerUsername,
            review.rating,
            review.description
        )
    }
}
