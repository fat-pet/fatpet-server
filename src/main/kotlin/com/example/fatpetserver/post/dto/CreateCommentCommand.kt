package com.example.fatpetserver.post.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

data class CreateCommentCommand(
    @Schema(example = "content")
    @field:NotBlank(message = "내용은 필수 입력값입니다.")
    val content: String,

    @Schema(example = "0")
    val parentId: Long? = null,
)
