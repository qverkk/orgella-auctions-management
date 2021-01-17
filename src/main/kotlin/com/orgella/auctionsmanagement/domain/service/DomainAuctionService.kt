package com.orgella.auctionsmanagement.domain.service

import com.orgella.auctionsmanagement.domain.AuctionEntity
import com.orgella.auctionsmanagement.domain.AuctionReviewsEntity
import com.orgella.auctionsmanagement.domain.repository.AuctionsRepository
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
            it.prive = price
            auctionsRepository.save(it)
        }
    }

    override fun findById(id: UUID): Optional<AuctionEntity> {
        return auctionsRepository.findById(id)
    }
}