package com.orgella.auctionsmanagement.domain.service

import com.orgella.auctionsmanagement.domain.AuctionReviewsEntity
import com.orgella.auctionsmanagement.domain.RatingsAndCount
import org.springframework.data.domain.Page
import java.util.*

interface ReviewService {
    fun save(reviewsEntity: AuctionReviewsEntity): AuctionReviewsEntity
    fun findAllReviewsForAuctionPath(auctionPath: String, page: Int): Page<AuctionReviewsEntity>
    fun sumReviewsCountAndRating(auctionPath: String): Optional<RatingsAndCount>

}
