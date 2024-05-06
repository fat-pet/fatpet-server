package com.example.fatpetserver.post.repository

import com.example.fatpetserver.member.repository.MemberInfo
import java.time.LocalDateTime

interface ReplyInfo {
    val id: Long
    val createdDate: LocalDateTime
    val content: String
    val member: MemberInfo
}
