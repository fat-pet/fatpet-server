package com.example.fatpetserver.post.repository

import com.example.fatpetserver.member.repository.MemberInfo
import java.time.LocalDateTime

interface PostInfo {
    val title: String
    val content: String
    val member: MemberInfo
    val createdDate: LocalDateTime
}
