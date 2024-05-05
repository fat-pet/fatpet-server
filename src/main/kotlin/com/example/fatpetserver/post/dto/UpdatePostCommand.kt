package com.example.fatpetserver.post.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

data class UpdatePostCommand(
    @Schema(example = "title")
    @field:NotBlank(message = "제목은 필수 입력값입니다.")
    val title: String = "",

    @Schema(example = "content")
    @field:NotBlank(message = "내용은 필수 입력값입니다.")
    val content: String = "",
)
