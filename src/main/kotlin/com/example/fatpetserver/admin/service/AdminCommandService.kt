package com.example.fatpetserver.admin.service

import com.example.fatpetserver.admin.dto.SignupAdminCommand
import com.example.fatpetserver.member.entity.Member
import com.example.fatpetserver.member.enums.Role
import com.example.fatpetserver.member.repository.MemberRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AdminCommandService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
) {

    fun signup(command: SignupAdminCommand): Member {
        val (loginId, password) = command

        val encodedPassword = passwordEncoder.encode(password)

        return memberRepository.save(
            Member(
                loginId = loginId,
                password = encodedPassword,
                role = Role.ADMIN,
            )
        )
    }
}
