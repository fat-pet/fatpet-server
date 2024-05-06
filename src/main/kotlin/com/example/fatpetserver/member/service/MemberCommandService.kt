package com.example.fatpetserver.member.service

import com.example.fatpetserver.member.dto.SignupCommand
import com.example.fatpetserver.member.dto.UpdateMemberCommand
import com.example.fatpetserver.member.entity.Member
import com.example.fatpetserver.member.repository.MemberRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberCommandService(
    private val memberRepository: MemberRepository,
    private val memberQueryService: MemberQueryService,
    private val passwordEncoder: PasswordEncoder,
) {

    fun signup(command: SignupCommand): Member {
        val (email, loginId, password, nickname) = command.apply {
            memberQueryService.checkLoginId(loginId)
            memberQueryService.checkNickname(nickname)
        }

        val encodedPassword = passwordEncoder.encode(password)

        return memberRepository.save(
            Member(
                email = email,
                loginId = loginId,
                password = encodedPassword,
                nickname = nickname,
            )
        )
    }

    fun delete(memberId: Long) =
        memberQueryService.getMemberByIdOrThrow(memberId).let { member ->
            memberRepository.delete(member)
        }

    fun update(memberId: Long, command: UpdateMemberCommand) {
        val (newEmail, newNickname) = command.apply {
            memberQueryService.checkNickname(nickname)
        }

        val updatedMember = memberQueryService.getMemberByIdOrThrow(memberId).apply {
            email = newEmail
            nickname = newNickname
        }

        memberRepository.save(updatedMember)
    }
}
