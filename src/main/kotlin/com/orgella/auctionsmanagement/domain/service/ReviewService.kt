package com.orgella.auctionsmanagement.domain.service

import com.orgella.auctionsmanagement.domain.AuctionReviewsEntity
import org.springframework.data.domain.Page

interface ReviewService {
    fun save(reviewsEntity: AuctionReviewsEntity): AuctionReviewsEntity
    fun findAllReviewsForAuctionPath(auctionPath: String, page: Int): Page<AuctionReviewsEntity>

}
