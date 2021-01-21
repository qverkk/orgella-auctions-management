package com.orgella.auctionsmanagement.domain.repository

import com.orgella.auctionsmanagement.domain.AuctionEntity
import java.util.*

interface AuctionsRepository {

    fun findById(id: UUID): Optional<AuctionEntity>

    fun save(auction: AuctionEntity)

    fun deleteAuctionById(id: UUID)

    fun findAll(): List<AuctionEntity>

    fun findAllContainingQuery(query: String): List<AuctionEntity>

    fun findAllContainingQueryAndCategory(query: String, category: String): List<AuctionEntity>

    fun findByAuctionPath(auctionPath: String): Optional<AuctionEntity>
}