package com.orgella.auctionsmanagement.application.mapper

import com.orgella.auctionsmanagement.application.response.GetAuctionDetailsResponse
import com.orgella.auctionsmanagement.application.response.GetAuctionItemResponse
import com.orgella.auctionsmanagement.application.response.GetAuctionResponse
import com.orgella.auctionsmanagement.domain.AuctionEntity
import org.bson.internal.Base64
import org.springframework.data.domain.Page
import java.util.stream.Collectors

object AuctionsMapper {
//    fun toGetAuctionResponse(auction: AuctionEntity): GetAuctionResponse {
//        return GetAuctionResponse(
//            auction.title,
//            auction.auctionPath,
//            auction.boughtQuantity,
//            auction.price,
//            Base64.encode(auction.thumbnail.data)
//        )
//    }

    fun toGetAuctionResponse(auction: Page<AuctionEntity>): GetAuctionResponse {
        return GetAuctionResponse(
            auction.number,
            auction.totalPages,
            auction.content.stream().map {
                GetAuctionItemResponse(
                    it.title,
                    it.auctionPath,
                    it.boughtQuantity,
                    it.price,
                    Base64.encode(it.thumbnail.data)
                )
            }.collect(Collectors.toList())
        )
    }

    fun toGetAuctionDetailsResponse(auction: AuctionEntity): GetAuctionDetailsResponse {
        return GetAuctionDetailsResponse(
            auction.title,
            auction.auctionPath,
            auction.sellerUsername,
            auction.quantity - auction.boughtQuantity,
            auction.boughtQuantity,
            auction.price,
            Base64.encode(auction.thumbnail.data),
            auction.description
        )
    }
}
