package com.example.fatpetserver.post.repository

import com.example.fatpetserver.member.repository.MemberInfo
import java.time.LocalDateTime

interface CommentInfo {
    val id: Long
    val createdDate: LocalDateTime
    val content: String
    val member: MemberInfo
    val children: List<ReplyInfo>
}
