package com.example.fatpetserver.member.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

data class SigninQuery(
    @Schema(example = "loginId")
    @field:NotBlank(message = "아이디는 필수 입력값입니다.")
    val loginId: String = "",
    @Schema(example = "password")
    @field:NotBlank(message = "비밀번호는 필수 입력값입니다.")
    val password: String = "",
)
