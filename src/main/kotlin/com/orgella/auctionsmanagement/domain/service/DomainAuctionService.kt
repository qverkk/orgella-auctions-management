package com.orgella.auctionsmanagement.domain.service

import com.orgella.auctionsmanagement.domain.AuctionEntity
import com.orgella.auctionsmanagement.domain.AuctionReviewsEntity
import com.orgella.auctionsmanagement.domain.repository.AuctionsRepository
import org.springframework.data.domain.Page
import java.math.BigDecimal
import java.util.*

class DomainAuctionService(
    private val auctionsRepository: AuctionsRepository
) : AuctionService {
    override fun createAuction(auction: AuctionEntity) {
        auctionsRepository.save(auction)
    }

    override fun deleteAuctionById(id: UUID) {
        auctionsRepository.deleteAuctionById(id)
    }

    override fun addReviewForAuction(id: UUID, review: AuctionReviewsEntity) {
        auctionsRepository.findById(id).ifPresent {
            it.reviews.add(review)
            auctionsRepository.save(it)
        }
    }

    override fun updateDescriptionForAuction(id: UUID, description: String) {
        auctionsRepository.findById(id).ifPresent {
            it.description = description
            auctionsRepository.save(it)
        }
    }

    override fun updatePriceForAuction(id: UUID, price: BigDecimal) {
        auctionsRepository.findById(id).ifPresent {
            it.price = price
            auctionsRepository.save(it)
        }
    }

    override fun findAllAuctions(): List<AuctionEntity> {
        return auctionsRepository.findAll()
    }

    override fun findReviewsForAuctionById(id: UUID): List<AuctionReviewsEntity> {
        val auction = auctionsRepository.findById(id).orElseGet { null } ?: return emptyList()

        return auction.reviews
    }

    override fun findAllContainingQuery(query: String): List<AuctionEntity> {
        return auctionsRepository.findAllContainingQuery(query)
    }

    override fun findAllContainingQuery(query: String, page: Int): Page<AuctionEntity> {
        return auctionsRepository.findAllContainingQuery(query, page)
    }

    override fun findAllContainingQueryAndCategory(query: String, category: String): List<AuctionEntity> {
        return auctionsRepository.findAllContainingQueryAndCategory(query, category)
    }

    override fun findAllContainingQueryAndCategory(
        query: String,
        category: String,
        page: Int
    ): Page<AuctionEntity> {
        return auctionsRepository.findAllContainingQueryAndCategory(query, category, page)
    }

    override fun findByAuctionPath(auctionPath: String): Optional<AuctionEntity> {
        return auctionsRepository.findByAuctionPath(auctionPath)
    }

    override fun findByAuctionPaths(auctionPaths: List<String>): List<AuctionEntity> {
        return auctionsRepository.findAllWithAuctionPaths(auctionPaths)
    }

    override fun findById(id: UUID): Optional<AuctionEntity> {
        return auctionsRepository.findById(id)
    }
}