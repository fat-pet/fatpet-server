package com.example.fatpetserver.member.repository

import com.example.fatpetserver.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {

    fun findMemberById(memberId: Long): MemberInfo?

    fun existsByLoginId(loginId: String): Boolean

    fun existsByNickname(nickname: String): Boolean

    fun findByLoginId(loginId: String): Member?
}
