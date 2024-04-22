package com.example.fatpetserver.member.service

import com.example.fatpetserver.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberQueryService(
    private val memberRepository: MemberRepository,
) {

    fun checkDuplicate(
        loginId: String?,
        nickname: String?,
    ): Boolean {
        loginId?.let {
            return runCatching { checkLoginId(loginId) }.isFailure
        }

        nickname?.let {
            return runCatching { checkNickname(nickname) }.isFailure
        }

        throw IllegalArgumentException("쿼리 파라미터 값을 입력해 주세요.")
    }

    fun checkLoginId(loginId: String) {
        require(memberRepository.existsByLoginId(loginId).not()) { "이미 사용 중인 아이디입니다." }
    }

    fun checkNickname(nickname: String) {
        require(memberRepository.existsByNickname(nickname).not()) { "이미 사용 중인 닉네임입니다." }
    }
}
