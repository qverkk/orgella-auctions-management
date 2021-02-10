package com.orgella.auctionsmanagement.infrastructure.repository.mongo

import com.orgella.auctionsmanagement.domain.AuctionReviewsEntity
import com.orgella.auctionsmanagement.domain.RatingsAndCount
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.core.aggregation.AggregationResults
import org.springframework.data.mongodb.repository.Aggregation
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface SpringDataMongoReviewRepository : MongoRepository<AuctionReviewsEntity, UUID> {

    fun findAllByAuctionPath(auctionPath: String, pageable: Pageable): Page<AuctionReviewsEntity>

    @Aggregation(
        pipeline = [
            "{\$match: {auctionPath: ?0}}",
            "{\$group:{_id: 0,totalRatings: { \$sum: '\$rating' },count: { \$sum: 1 }}}",
            "{\$project: {_id: 0,totalRatings: 1,count: 1}}"
        ]
    )
    fun sumRatingsAndCountReviews(auctionPath: String): AggregationResults<RatingsAndCount>
}