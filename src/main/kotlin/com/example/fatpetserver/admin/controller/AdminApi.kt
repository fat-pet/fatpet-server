package com.example.fatpetserver.admin.controller

import com.example.fatpetserver.admin.dto.SignupAdminCommand
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "Admin API")
interface AdminApi {
    @Operation(summary = "어드민 회원가입")
    fun signup(command: SignupAdminCommand)
}
