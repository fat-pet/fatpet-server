package com.example.fatpetserver.member.service

import com.example.fatpetserver.common.auth.JwtTokenProvider
import com.example.fatpetserver.member.dto.SigninRequest
import com.example.fatpetserver.member.dto.SigninResponse
import com.example.fatpetserver.member.dto.SignupRequest
import com.example.fatpetserver.member.entity.Member
import com.example.fatpetserver.member.repository.MemberRepository
import org.apache.coyote.BadRequestException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberAuthService(
    private val repository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider,
    private val memberQueryService: MemberQueryService,
) {

    @Transactional
    fun signup(request: SignupRequest): Member {
        val (email, loginId, password, nickname) = request.apply {
            memberQueryService.checkLoginId(loginId)
            memberQueryService.checkNickname(nickname)
        }

        val encodedPassword = passwordEncoder.encode(password)

        return repository.save(
            Member(
                email = email,
                loginId = loginId,
                password = encodedPassword,
                nickname = nickname,
            )
        )
    }

    @Transactional(readOnly = true)
    fun signin(request: SigninRequest): SigninResponse {
        val (loginId, password) = request

        val member = repository.findByLoginId(loginId)?.takeIf { member ->
            passwordEncoder.matches(password, member.password)
        } ?: throw BadRequestException("로그인 실패")

        val token = jwtTokenProvider.createToken(
            id = member.id.toString(),
            role = member.role.toString()
        )

        return SigninResponse(
            role = member.role,
            token = token
        )
    }
}
