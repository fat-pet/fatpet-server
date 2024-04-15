package com.example.fatpetserver.common.auth

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*


@Service
class JwtTokenProvider {

    @Value("\${jwt.secret}")
    lateinit var secret: String
    private val key by lazy {
        Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret))
    }

    fun createToken(id: String): String {
        val now = Date()
        val expirationDate = Date(now.time + EXPIRATION_MILLISECONDS)

        return Jwts
            .builder()
            .subject(id)
            .issuedAt(now)
            .expiration(expirationDate)
            .signWith(key)
            .compact()
    }

    companion object {
        private const val EXPIRATION_MILLISECONDS = 1000L * 60 * 60 * 24 * 30
    }
}
