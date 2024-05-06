package com.example.fatpetserver.member.service

import com.example.fatpetserver.common.auth.JwtTokenProvider
import com.example.fatpetserver.member.dto.SigninQuery
import com.example.fatpetserver.member.dto.SigninResponse
import com.example.fatpetserver.member.entity.Member
import com.example.fatpetserver.member.repository.MemberRepository
import org.apache.coyote.BadRequestException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberQueryService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider,
) {

    fun getMemberInfo(memberId: Long) =
        memberRepository.findMemberById(memberId)
            ?: throw IllegalArgumentException("존재하지 않는 사용자입니다.")

    fun signin(query: SigninQuery): SigninResponse {
        val (loginId, password) = query

        val member = memberRepository.findByLoginId(loginId)?.takeIf { member ->
            passwordEncoder.matches(password, member.password)
        } ?: throw BadRequestException("로그인 실패")

        val token = jwtTokenProvider.createToken(
            id = member.id.toString(),
            role = member.role.toString(),
        )

        return SigninResponse(member.role, token)
    }

    fun getMemberByIdOrThrow(memberId: Long): Member =
        memberRepository.findByIdOrNull(memberId)
            ?: throw IllegalArgumentException("존재하지 않는 사용자입니다.")

    fun checkDuplicate(loginId: String?, nickname: String?): Boolean {
        loginId?.let {
            return runCatching { checkLoginId(loginId) }.isFailure
        }

        nickname?.let {
            return runCatching { checkNickname(nickname) }.isFailure
        }

        throw IllegalArgumentException("쿼리 파라미터 값을 입력해 주세요.")
    }

    fun checkLoginId(loginId: String) =
        require(memberRepository.existsByLoginId(loginId).not()) { "이미 사용 중인 아이디입니다." }

    fun checkNickname(nickname: String) =
        require(memberRepository.existsByNickname(nickname).not()) { "이미 사용 중인 닉네임입니다." }
}
