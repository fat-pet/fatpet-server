package com.example.fatpetserver.post.dto

import com.example.fatpetserver.post.repository.CommentInfo
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

data class PostDetailResponse(
    @Schema(example = "2024-05-03T10:15:30")
    val createdDate: LocalDateTime,

    @Schema(example = "nickname")
    val nickname: String,

    @Schema(example = "title")
    val title: String,

    @Schema(example = "content")
    val content: String,

    val comments: List<CommentInfo>,
)
