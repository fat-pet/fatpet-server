package com.example.fatpetserver.common.auth

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class AuthenticationToken(
    private val principal: UserDetails,
    authorities: Collection<GrantedAuthority>,
) : AbstractAuthenticationToken(authorities) {

    override fun getCredentials(): Any? = null

    override fun getPrincipal() = principal
}
