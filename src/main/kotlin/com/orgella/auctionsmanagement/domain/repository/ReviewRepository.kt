package com.orgella.auctionsmanagement.domain.repository

import com.orgella.auctionsmanagement.domain.AuctionReviewsEntity
import com.orgella.auctionsmanagement.domain.RatingsAndCount
import org.springframework.data.domain.Page
import org.springframework.data.mongodb.core.aggregation.AggregationResults
import java.util.*

interface ReviewRepository {
    fun save(reviewsEntity: AuctionReviewsEntity): AuctionReviewsEntity
    fun findAllReviewsForAuctionPath(auctionPath: String, page: Int): Page<AuctionReviewsEntity>
    fun sumReviewsCountAndRating(auctionPath: String): AggregationResults<RatingsAndCount>
}
