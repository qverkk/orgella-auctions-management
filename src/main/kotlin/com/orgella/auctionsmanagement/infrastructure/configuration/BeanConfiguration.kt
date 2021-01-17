package com.orgella.auctionsmanagement.infrastructure.configuration

import com.orgella.auctionsmanagement.domain.repository.AuctionsRepository
import com.orgella.auctionsmanagement.domain.service.AuctionService
import com.orgella.auctionsmanagement.domain.service.DomainAuctionService
import feign.Logger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
class BeanConfiguration {

    @Bean
    fun auctionsService(auctionsRepository: AuctionsRepository): AuctionService {
        return DomainAuctionService(auctionsRepository)
    }

    @Bean
    @Profile("production")
    fun feignProductionLoggingLevel(): Logger.Level = Logger.Level.NONE

    @Bean
    @Profile("!production")
    fun feignDefaultFeignLoggingLevel(): Logger.Level = Logger.Level.FULL
}