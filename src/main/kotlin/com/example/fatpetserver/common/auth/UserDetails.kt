package com.example.fatpetserver.common.auth

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class UserDetails(
    private val _id: String,
    private val _authorities: List<SimpleGrantedAuthority>,
) : UserDetails {

    val id = _id
    val role = _authorities.joinToString("")

    override fun getAuthorities() = _authorities

    override fun getPassword(): String? = null

    override fun getUsername(): String? = null

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}
