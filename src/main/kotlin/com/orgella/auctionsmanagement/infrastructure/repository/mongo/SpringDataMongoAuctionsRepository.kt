package com.orgella.auctionsmanagement.infrastructure.repository.mongo

import com.orgella.auctionsmanagement.domain.AuctionEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface SpringDataMongoAuctionsRepository : MongoRepository<AuctionEntity, UUID> {

    fun findAllByTitleIsContaining(title: String): List<AuctionEntity>

    fun findAllByTitleIsContainingAndCategory(title: String, category: String): List<AuctionEntity>

    fun findByAuctionPath(auctionPath: String): Optional<AuctionEntity>

    fun findAllByAuctionPathIn(auctionPaths: List<String>): List<AuctionEntity>

    fun findAllByTitleIsContaining(title: String, pageable: Pageable): Page<AuctionEntity>

    fun findAllByTitleIsContainingAndCategory(title: String, category: String, pageable: Pageable): Page<AuctionEntity>
}