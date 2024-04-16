package com.example.fatpetserver.member.service

import com.example.fatpetserver.member.dto.UpdateMemberCommand
import com.example.fatpetserver.member.repository.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberCommandService(
    private val repository: MemberRepository,
    private val memberQueryService: MemberQueryService,
) {

    fun delete(id: Long) {
        repository.findByIdOrNull(id)?.let { member ->
            repository.delete(member)
        } ?: throw IllegalArgumentException("존재하지 않는 사용자입니다.")
    }

    fun update(id: Long, command: UpdateMemberCommand) {
        val (newEmail, newNickname) = command.apply {
            memberQueryService.checkNickname(nickname)
        }

        val updatedMember = repository.findByIdOrNull(id)?.apply {
            email = newEmail
            nickname = newNickname
        } ?: throw IllegalArgumentException("존재하지 않는 사용자입니다.")

        repository.save(updatedMember)
    }
}
