package com.orgella.auctionsmanagement.infrastructure.repository.mongo

import com.orgella.auctionsmanagement.domain.AuctionReviewsEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface SpringDataMongoReviewRepository : MongoRepository<AuctionReviewsEntity, UUID> {

    fun findAllByAuctionPath(auctionPath: String, pageable: Pageable): Page<AuctionReviewsEntity>
}