package com.example.fatpetserver.member.dto

import com.example.fatpetserver.member.entity.Role

data class SigninResponse(
    val role: Role,
    val token: String,
)
