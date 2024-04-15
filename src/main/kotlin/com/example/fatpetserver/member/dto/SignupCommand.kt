package com.example.fatpetserver.member.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length

data class SignupCommand(
    @field:NotBlank(message = "이메일은 필수 입력값입니다.")
    @field:Email(message = "이메일 형식이 올바르지 않습니다.")
    val email: String = "",

    @field:NotBlank(message = "아이디는 필수 입력값입니다.")
    @field:Length(min = 4, max = 12, message = "아이디는 4글자, 이상 12글자 이하여야 합니다.")
    val loginId: String = "",

    @field:NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @field:Length(min = 6, max = 16, message = "비밀번호는 6글자, 이상 16글자 이하여야 합니다.")
    val password: String = "",

    @field:NotBlank(message = "닉네임은 필수 입력값입니다.")
    @field:Length(min = 2, max = 10, message = "닉네임은 2글자, 이상 10글자 이하여야 합니다.")
    val nickname: String = "",
)
