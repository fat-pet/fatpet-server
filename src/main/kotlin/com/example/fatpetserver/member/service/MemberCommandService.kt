package com.example.fatpetserver.member.service

import com.example.fatpetserver.member.dto.CreateMemberCommand
import com.example.fatpetserver.member.entity.Member
import com.example.fatpetserver.member.repository.MemberRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberCommandService(
    private val repository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
) {

    fun create(command: CreateMemberCommand): Member {
        val (email, loginId, password, nickname) = command
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
}
