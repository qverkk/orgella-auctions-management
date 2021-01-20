package com.orgella.auctionsmanagement.application.mapper

import com.orgella.auctionsmanagement.application.response.GetAuctionResponse
import com.orgella.auctionsmanagement.domain.AuctionEntity
import java.util.stream.Collectors

object AuctionsMapper {
    fun toResponse(auction: AuctionEntity): GetAuctionResponse {
        return GetAuctionResponse(
            auction.title,
            auction.auctionPath,
            auction.sellerUsername,
            auction.quantity,
            auction.boughtQuantity,
            auction.price,
            auction.category,
            auction.reviews.stream().map { review ->
                ReviewsMapper.toResponse(review)
            }.collect(Collectors.toList()),
            auction.thumbnail,
            auction.description
        )
    }

}
