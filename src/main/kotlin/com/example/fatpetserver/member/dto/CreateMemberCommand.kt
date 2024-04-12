package com.example.fatpetserver.member.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length

data class CreateMemberCommand(
    @field:NotBlank(message = "이메일은 필수 입력값입니다.")
    @field:Email
    val email: String,

    @field:NotBlank(message = "아이디는 필수 입력값입니다.")
    @field:Length(min = 4, max = 12)
    val loginId: String,

    @field:NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @field:Length(min = 6, max = 16)
    val password: String,

    @field:NotBlank(message = "닉네임은 필수 입력값입니다.")
    @field:Length(min = 2, max = 10)
    val nickname: String,
)
