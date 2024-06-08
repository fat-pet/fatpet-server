package com.example.fatpetserver.member.dto

import com.example.fatpetserver.member.enums.Role
import io.swagger.v3.oas.annotations.media.Schema

data class SigninResponse(
    @Schema(example = "MEMBER")
    val role: Role,
    @Schema(
        example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIwIiwicm9sZSI6IkVYQU1QTEUiLCJpYXQiOjE3MTMxOTY1NjEsImV4cCI6MTcxNTc4ODU2MX0.VkMATOtPMQcVf9gM9NpM50EQab61eexWnzQBOaN_NVY",
    )
    val token: String,
)
