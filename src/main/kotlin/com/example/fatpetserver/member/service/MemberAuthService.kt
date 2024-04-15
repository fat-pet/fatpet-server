package com.example.fatpetserver.member.service

import com.example.fatpetserver.common.auth.JwtTokenProvider
import com.example.fatpetserver.member.dto.SigninQuery
import com.example.fatpetserver.member.dto.SigninResponse
import com.example.fatpetserver.member.dto.SignupCommand
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
) {

    @Transactional
    fun signup(command: SignupCommand): Member {
        val (email, loginId, password, nickname) = command.apply {
            checkLoginId(loginId)
            checkNickname(nickname)
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
    fun signin(query: SigninQuery): SigninResponse {
        val (loginId, password) = query

        val member =
            repository.findByLoginId(loginId)?.takeIf {
                passwordEncoder.matches(password, it.password)
            } ?: throw BadRequestException("로그인 실패")

        val token =
            jwtTokenProvider.createToken(id = member.id.toString(), role = member.role.toString())

        return SigninResponse(
            role = member.role,
            token = token
        )
    }

    private fun checkLoginId(loginId: String) {
        require(repository.existsByLoginId(loginId).not()) { "이미 사용 중인 아이디입니다." }
    }

    private fun checkNickname(nickname: String) {
        require(repository.existsByNickname(nickname).not()) { "이미 사용 중인 닉네임입니다." }
    }
}
