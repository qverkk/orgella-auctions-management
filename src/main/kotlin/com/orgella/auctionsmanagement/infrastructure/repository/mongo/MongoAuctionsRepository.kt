package com.orgella.auctionsmanagement.infrastructure.repository.mongo

import com.orgella.auctionsmanagement.domain.AuctionEntity
import com.orgella.auctionsmanagement.domain.repository.AuctionsRepository
import org.springframework.context.annotation.Primary
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
}