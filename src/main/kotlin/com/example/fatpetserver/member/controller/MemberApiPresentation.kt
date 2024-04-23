package com.example.fatpetserver.member.controller

import com.example.fatpetserver.common.ApiResponse
import com.example.fatpetserver.common.auth.UserDetails
import com.example.fatpetserver.member.dto.SigninQuery
import com.example.fatpetserver.member.dto.SigninResponse
import com.example.fatpetserver.member.dto.SignupCommand
import com.example.fatpetserver.member.dto.UpdateMemberCommand
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "Member API")
interface MemberApiPresentation {

    @Operation(summary = "회원가입")
    fun signup(command: SignupCommand)

    @Operation(summary = "로그인")
    fun signin(query: SigninQuery): ApiResponse<SigninResponse>

    @Operation(summary = "회원 탈퇴")
    fun delete(userDetails: UserDetails)

    @Operation(summary = "회원 정보 수정")
    fun update(userDetails: UserDetails, command: UpdateMemberCommand)

    @Operation(summary = "아이디/닉네임 중복 확인")
    fun checkDuplicate(loginId: String?, nickname: String?): ApiResponse<Boolean>
}
