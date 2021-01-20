package com.orgella.auctionsmanagement.infrastructure.repository.mongo

import com.orgella.auctionsmanagement.domain.AuctionEntity
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface SpringDataMongoAuctionsRepository : MongoRepository<AuctionEntity, UUID> {

    fun findAllByTitleIsContaining(title: String): List<AuctionEntity>

    fun findAllByTitleIsContainingAndCategory(title: String, category: String): List<AuctionEntity>
}