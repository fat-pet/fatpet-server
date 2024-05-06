package com.example.fatpetserver.post.dto

import com.example.fatpetserver.post.repository.CommentInfo
import java.time.LocalDateTime

data class PostDetailResponse(
    val createdDate: LocalDateTime,
    val nickname: String,
    val title: String,
    val content: String,
    val comments: List<CommentInfo>,
)
