package com.example.fatpetserver.post.repository

import com.example.fatpetserver.member.repository.MemberInfo
import java.time.LocalDateTime

interface PostInfo {
    val id: Long
    val createdDate: LocalDateTime
    val title: String
    val content: String
    val member: MemberInfo
}
