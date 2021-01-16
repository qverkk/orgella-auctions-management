package com.orgella.auctionsmanagement.application.feign

import feign.FeignException
import feign.hystrix.FallbackFactory
import org.slf4j.LoggerFactory
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "users-ws", fallbackFactory = UsersFallbackFactory::class)
interface UsersServiceClient {

    @GetMapping("/users/{username}/capableToSell")
    fun isUserCapableToSell(@PathVariable username: String): Boolean
}

@Component
internal class UsersFallbackFactory : FallbackFactory<UsersServiceClient> {
    override fun create(cause: Throwable): UsersServiceClient {
        return UsersServiceFallback(cause)
    }
}

internal class UsersServiceFallback(
    private val cause: Throwable? = null
) : UsersServiceClient {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    override fun isUserCapableToSell(username: String): Boolean {
        if (cause is FeignException
            && cause.status() == 404
        ) {
            logger.error(
                "404 error took place when isUserCapableToSell was called with userId: "
                        + username + ". Error message: "
                        + cause.getLocalizedMessage()
            )
        } else {
            logger.error(
                "Other error took place: " + cause!!.localizedMessage
            )
        }
        return false
    }
}