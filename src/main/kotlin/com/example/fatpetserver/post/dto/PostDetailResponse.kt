package com.example.fatpetserver.post.dto

import com.example.fatpetserver.post.repository.CommentInfo
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

data class PostDetailResponse(
    @Schema(example = "1")
    val id: Long,
    @Schema(example = "2024-05-03T10:15:30")
    val createdDate: LocalDateTime,
    @Schema(example = "nickname")
    val nickname: String,
    @Schema(example = "title")
    val title: String,
    @Schema(example = "content")
    val content: String,
    @Schema(
        example = """
            [
                { "id": 1, "createdDate": "2024-05-03T11:15:30", "content": "content", "member": { "email" : "email", "loginId": "loginId", "nickname": "nickname" }, "children": [{ "id": 3, "createdDate": "2024-05-03T11:30:00", "content": "content", "member": { "email" : "email", "loginId": "loginId", "nickname": "nickname" } }] },
                { "id": 2, "createdDate": "2024-05-03T11:20:00", "content": "content", "member": { "email" : "email", "loginId": "loginId", "nickname": "nickname" }, "children": [] }
            ]
        """,
    )
    val comments: List<CommentInfo>,
)
