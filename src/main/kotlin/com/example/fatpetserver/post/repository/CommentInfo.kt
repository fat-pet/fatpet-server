package com.example.fatpetserver.post.repository

import com.example.fatpetserver.member.repository.MemberInfo

interface CommentInfo {
    val id: Long
    val content: String
    val member: MemberInfo
    val children: List<CommentInfo>
}
