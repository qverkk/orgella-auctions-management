package com.orgella.auctionsmanagement.application.mapper

import com.orgella.auctionsmanagement.application.response.GetAuctionDetailsResponse
import com.orgella.auctionsmanagement.application.response.GetAuctionResponse
import com.orgella.auctionsmanagement.domain.AuctionEntity
import org.bson.internal.Base64
import java.util.stream.Collectors

object AuctionsMapper {
    fun toGetAuctionResponse(auction: AuctionEntity): GetAuctionResponse {
        return GetAuctionResponse(
            auction.title,
            auction.auctionPath,
            auction.boughtQuantity,
            auction.price,
            Base64.encode(auction.thumbnail.data)
        )
    }

    fun toGetAuctionDetailsResponse(auction: AuctionEntity): GetAuctionDetailsResponse {
        return GetAuctionDetailsResponse(
            auction.title,
            auction.auctionPath,
            auction.sellerUsername,
            auction.quantity,
            auction.boughtQuantity,
            auction.price,
            auction.reviews.stream().map { review ->
                ReviewsMapper.toResponse(review)
            }.collect(Collectors.toList()),
            Base64.encode(auction.thumbnail.data),
            auction.description
        )
    }
}
