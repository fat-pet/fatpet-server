package com.example.fatpetserver.member.dto

import com.example.fatpetserver.member.entity.Role
import io.swagger.v3.oas.annotations.media.Schema

data class SigninResponse(
    @Schema(description = "권한", example = "MEMBER")
    val role: Role,

    val token: String,
)
