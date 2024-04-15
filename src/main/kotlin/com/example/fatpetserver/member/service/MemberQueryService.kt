package com.example.fatpetserver.member.service

import com.example.fatpetserver.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberQueryService(
    private val repository: MemberRepository,
)
