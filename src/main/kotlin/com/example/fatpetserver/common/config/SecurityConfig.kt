package com.example.fatpetserver.common.config

import com.example.fatpetserver.common.auth.JwtAuthFilter
import com.example.fatpetserver.common.auth.JwtTokenProvider
import com.example.fatpetserver.member.enums.Role
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtTokenProvider: JwtTokenProvider,
) {

    private val allowedRequests =
        arrayOf(
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/api/members/signup",
            "/api/members/signin",
            "/api/members/check",
        )

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .httpBasic { it.disable() }
            .csrf { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests {
                it.requestMatchers(*allowedRequests).permitAll()
                it.requestMatchers(HttpMethod.GET, "/api/posts").permitAll()
                it.requestMatchers("/api/admin/**").hasAuthority(Role.ADMIN.name)
                it.anyRequest().hasAuthority(Role.MEMBER.name)
            }
            .formLogin { it.disable() }
            .addFilterBefore(
                JwtAuthFilter(jwtTokenProvider),
                UsernamePasswordAuthenticationFilter::class.java,
            )

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}
