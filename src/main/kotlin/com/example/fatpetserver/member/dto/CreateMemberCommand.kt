package com.example.fatpetserver.member.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length

data class CreateMemberCommand(
    @field:NotBlank
    @field:Email
    val email: String,

    @field:NotBlank
    @field:Length(min = 4, max = 12)
    val loginId: String,

    @field:NotBlank
    @field:Length(min = 6, max = 16)
    val password: String,

    @field:NotBlank
    @field:Length(min = 2, max = 10)
    val nickname: String,
)
