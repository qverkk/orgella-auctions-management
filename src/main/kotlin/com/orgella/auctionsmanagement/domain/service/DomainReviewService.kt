package com.orgella.auctionsmanagement.domain.service

import com.orgella.auctionsmanagement.domain.AuctionReviewsEntity
import com.orgella.auctionsmanagement.domain.repository.ReviewRepository
import org.springframework.data.domain.Page

class DomainReviewService(
    private val repository: ReviewRepository
) : ReviewService {
    override fun save(reviewsEntity: AuctionReviewsEntity): AuctionReviewsEntity {
        return repository.save(reviewsEntity)
    }

    override fun findAllReviewsForAuctionPath(auctionPath: String, page: Int): Page<AuctionReviewsEntity> {
        return repository.findAllReviewsForAuctionPath(auctionPath, page)
    }

}