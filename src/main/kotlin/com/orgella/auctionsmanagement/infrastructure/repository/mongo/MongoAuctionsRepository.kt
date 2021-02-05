package com.orgella.auctionsmanagement.infrastructure.repository.mongo

import com.orgella.auctionsmanagement.domain.AuctionEntity
import com.orgella.auctionsmanagement.domain.repository.AuctionsRepository
import org.springframework.context.annotation.Primary
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component
import java.util.*

@Component
@Primary
class MongoAuctionsRepository(
    val auctionRepository: SpringDataMongoAuctionsRepository
) : AuctionsRepository {
    override fun findById(id: UUID): Optional<AuctionEntity> {
        return auctionRepository.findById(id)
    }

    override fun save(auction: AuctionEntity) {
        auctionRepository.save(auction)
    }

    override fun deleteAuctionById(id: UUID) {
        auctionRepository.deleteById(id)
    }

    override fun findAll(): List<AuctionEntity> {
        return auctionRepository.findAll()
    }

    override fun findAllContainingQuery(query: String): List<AuctionEntity> {
        return auctionRepository.findAllByTitleIsContaining(query)
    }

    override fun findAllContainingQuery(query: String, page: Int): Page<AuctionEntity> {
        val pageable = PageRequest.of(page, 20)
        return auctionRepository.findAllByTitleIsContaining(query, pageable)
    }

    override fun findAllContainingQueryAndCategory(query: String, category: String): List<AuctionEntity> {
        return auctionRepository.findAllByTitleIsContainingAndCategory(query, category)
    }

    override fun findAllContainingQueryAndCategory(
        query: String,
        category: String,
        page: Int
    ): Page<AuctionEntity> {
        val pageable = PageRequest.of(page, 20)
        return auctionRepository.findAllByTitleIsContainingAndCategory(query, category, pageable)
    }

    override fun findByAuctionPath(auctionPath: String): Optional<AuctionEntity> {
        return auctionRepository.findByAuctionPath(auctionPath)
    }

    override fun findAllWithAuctionPaths(auctionPaths: List<String>): List<AuctionEntity> {
        return auctionRepository.findAllByAuctionPathIn(auctionPaths)
    }
}