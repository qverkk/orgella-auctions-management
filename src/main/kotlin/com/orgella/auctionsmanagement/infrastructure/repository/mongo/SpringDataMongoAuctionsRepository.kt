package com.orgella.auctionsmanagement.infrastructure.repository.mongo

import com.orgella.auctionsmanagement.domain.AuctionEntity
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface SpringDataMongoAuctionsRepository : MongoRepository<AuctionEntity, UUID> {

}