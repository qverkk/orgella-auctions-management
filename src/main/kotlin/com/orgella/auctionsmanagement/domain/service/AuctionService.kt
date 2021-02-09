package com.orgella.auctionsmanagement.domain.service

import com.orgella.auctionsmanagement.domain.AuctionEntity
import com.orgella.auctionsmanagement.domain.AuctionReviewsEntity
import org.springframework.data.domain.Page
import java.math.BigDecimal
import java.util.*

interface AuctionService {

    fun createAuction(auction: AuctionEntity)

    fun deleteAuctionById(id: UUID)

    fun findById(id: UUID): Optional<AuctionEntity>

    fun updateDescriptionForAuction(id: UUID, description: String)

    fun updatePriceForAuction(id: UUID, price: BigDecimal)

    fun findAllAuctions(): List<AuctionEntity>

    fun findAllContainingQuery(query: String): List<AuctionEntity>

    fun findAllContainingQuery(query: String, page: Int): Page<AuctionEntity>

    fun findAllContainingQueryAndCategory(query: String, category: String): List<AuctionEntity>

    fun findAllContainingQueryAndCategory(query: String, category: String, page: Int): Page<AuctionEntity>

    fun findByAuctionPath(auctionPath: String): Optional<AuctionEntity>

    fun findByAuctionPaths(auctionPaths: List<String>): List<AuctionEntity>

    fun update(auction: AuctionEntity)
}
