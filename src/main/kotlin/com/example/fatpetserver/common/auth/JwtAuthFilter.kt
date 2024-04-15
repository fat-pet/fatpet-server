package com.example.fatpetserver.common.auth

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean

class JwtAuthFilter(
    private val jwtTokenProvider: JwtTokenProvider,
) : GenericFilterBean() {

    override fun doFilter(
        request: ServletRequest,
        response: ServletResponse,
        chain: FilterChain,
    ) {
        resolveToken(request as HttpServletRequest)?.let { token ->
            if (jwtTokenProvider.validateToken(token)) {
                val authentication = jwtTokenProvider.getAuthentication(token)
                SecurityContextHolder.getContext().authentication = authentication
            }
        }

        chain.doFilter(request, response)
    }

    private fun resolveToken(request: HttpServletRequest): String? {
        return request.getHeader("Authorization")?.let { header ->
            if (header.startsWith("Bearer ")) {
                header.replace("Bearer ", "")
            } else {
                null
            }
        }
    }
}