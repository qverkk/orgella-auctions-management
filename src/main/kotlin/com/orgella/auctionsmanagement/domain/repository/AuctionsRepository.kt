package com.orgella.auctionsmanagement.domain.repository

import com.orgella.auctionsmanagement.domain.AuctionEntity
import java.util.*

interface AuctionsRepository {

    fun findById(id: UUID): Optional<AuctionEntity>

    fun save(auction: AuctionEntity)
    
    fun deleteAuctionById(id: UUID)
}