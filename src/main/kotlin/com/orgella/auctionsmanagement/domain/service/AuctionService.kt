package com.orgella.auctionsmanagement.domain.service

import com.orgella.auctionsmanagement.domain.AuctionEntity
import com.orgella.auctionsmanagement.domain.AuctionReviewsEntity
import java.math.BigDecimal
import java.util.*

interface AuctionService {

    fun createAuction(auction: AuctionEntity)

    fun deleteAuctionById(id: UUID)

    fun addReviewForAuction(id: UUID, review: AuctionReviewsEntity)

    fun findById(id: UUID): Optional<AuctionEntity>

    fun updateDescriptionForAuction(id: UUID, description: String)

    fun updatePriceForAuction(id: UUID, price: BigDecimal)

    fun findAllAuctions(): List<AuctionEntity>

    fun findReviewsForAuctionById(id: UUID): List<AuctionReviewsEntity>

    fun findAllContainingQuery(query: String): List<AuctionEntity>

    fun findAllContainingQueryAndCategory(query: String, category: String): List<AuctionEntity>

    fun findByAuctionPath(auctionPath: String): Optional<AuctionEntity>
}
