package com.example.fatpetserver.member.service

import com.example.fatpetserver.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberQueryService(
    private val repository: MemberRepository,
) {
    
    fun checkLoginId(loginId: String) {
        require(repository.existsByLoginId(loginId).not()) { "이미 사용 중인 아이디입니다." }
    }

    fun checkNickname(nickname: String) {
        require(repository.existsByNickname(nickname).not()) { "이미 사용 중인 닉네임입니다." }
    }
}
