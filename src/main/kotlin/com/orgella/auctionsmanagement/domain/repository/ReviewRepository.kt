package com.orgella.auctionsmanagement.domain.repository

import com.orgella.auctionsmanagement.domain.AuctionReviewsEntity
import org.springframework.data.domain.Page

interface ReviewRepository {
    fun save(reviewsEntity: AuctionReviewsEntity): AuctionReviewsEntity
    fun findAllReviewsForAuctionPath(auctionPath: String, page: Int): Page<AuctionReviewsEntity>
}
