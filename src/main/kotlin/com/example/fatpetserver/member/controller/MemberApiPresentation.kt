package com.example.fatpetserver.member.controller

import com.example.fatpetserver.common.ApiResponse
import com.example.fatpetserver.member.dto.SigninQuery
import com.example.fatpetserver.member.dto.SigninResponse
import com.example.fatpetserver.member.dto.SignupCommand
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "Member API")
interface MemberApiPresentation {

    @Operation(summary = "회원가입")
    fun signup(command: SignupCommand)


    @Operation(summary = "로그인")
    fun signin(query: SigninQuery): ApiResponse<SigninResponse>
}
