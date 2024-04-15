package com.example.fatpetserver.common.auth

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Service
import java.util.*


@Service
class JwtTokenProvider {

    @Value("\${jwt.secret}")
    lateinit var secret: String
    private val key by lazy {
        Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret))
    }

    fun createToken(id: String, role: String): String {
        val now = Date()
        val expirationDate = Date(now.time + EXPIRATION_MILLISECONDS)
        val claims = mapOf("role" to role)

        return Jwts.builder()
            .subject(id)
            .claims(claims)
            .issuedAt(now)
            .expiration(expirationDate)
            .signWith(key)
            .compact()
    }

    fun validateToken(token: String): Boolean = try {
        getClaims(token)
        true
    } catch (exception: Exception) {
        false
    }

    fun getAuthentication(token: String): Authentication {
        val claims: Claims = getClaims(token)
        val id = claims.subject
        val role = claims["role"] as String
        val authorities = listOf(SimpleGrantedAuthority(role))
        val principal = UserDetails(
            _id = id,
            _authorities = authorities
        )

        return UsernamePasswordAuthenticationToken(principal, "", authorities)
    }

    private fun getClaims(token: String): Claims =
        Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .payload

    companion object {
        private const val EXPIRATION_MILLISECONDS = 1000L * 60 * 60 * 24 * 30
    }
}
