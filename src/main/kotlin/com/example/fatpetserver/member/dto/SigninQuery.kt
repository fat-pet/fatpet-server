package com.example.fatpetserver.member.dto

import jakarta.validation.constraints.NotBlank

data class SigninQuery(
    @field:NotBlank(message = "아이디는 필수 입력값입니다.")
    val loginId: String = "",

    @field:NotBlank(message = "비밀번호는 필수 입력값입니다.")
    val password: String = "",
)
