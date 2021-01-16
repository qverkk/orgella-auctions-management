package com.orgella.auctionsmanagement.infrastructure.configuration.security

import io.jsonwebtoken.Jwts
import org.springframework.core.env.Environment
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.util.stream.Collectors
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthorizationFilter(
    authenticationManager: AuthenticationManager,
    private val env: Environment
) : BasicAuthenticationFilter(authenticationManager) {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val authorizationHeader: String? = request.getHeader(env.getProperty("authorization.token.header.name"))

        if (authorizationHeader == null || !authorizationHeader.startsWith(env.getProperty("authorization.token.header.prefix")!!)) {
            chain.doFilter(request, response)
            return
        }

        val authentication = getAuthentication(request)

        SecurityContextHolder.getContext().authentication = authentication
        chain.doFilter(request, response)
    }

    private fun getAuthentication(request: HttpServletRequest): UsernamePasswordAuthenticationToken? {
        val authorizationHeader: String = request.getHeader(env.getProperty("authorization.token.header.name"))
            ?: return null

        val token = authorizationHeader.replace(env.getProperty("authorization.token.header.prefix")!!, "")

        val jwtBody = Jwts.parser()
            .setSigningKey(env.getProperty("token.secret"))
            .parseClaimsJws(token)
            .body
        
        val userId = jwtBody.subject ?: return null

        val roles = (jwtBody["roles"] as String)
            .replace("[", "")
            .replace("]", "")
            .split(",")
            .stream()
            .map { SimpleGrantedAuthority(it) }
            .collect(Collectors.toList())

        return UsernamePasswordAuthenticationToken(userId, null, roles)
    }
}
