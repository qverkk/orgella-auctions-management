package com.orgella.auctionsmanagement.infrastructure.repository.mongo

import com.orgella.auctionsmanagement.domain.AuctionReviewsEntity
import com.orgella.auctionsmanagement.domain.repository.ReviewRepository
import org.springframework.context.annotation.Primary
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

@Component
@Primary
class MongoReviewRepository(
    val repository: SpringDataMongoReviewRepository
) : ReviewRepository {
    override fun save(reviewsEntity: AuctionReviewsEntity): AuctionReviewsEntity {
        return repository.save(reviewsEntity)
    }

    override fun findAllReviewsForAuctionPath(auctionPath: String, page: Int): Page<AuctionReviewsEntity> {
        val pageable = PageRequest.of(page, 20)
        return repository.findAllByAuctionPath(auctionPath, pageable)
    }

}