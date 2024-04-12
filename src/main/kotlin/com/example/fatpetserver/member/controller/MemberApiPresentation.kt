package com.example.fatpetserver.member.controller

import com.example.fatpetserver.common.ApiResponse
import com.example.fatpetserver.member.dto.CreateMemberCommand
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "Member API")
interface MemberApiPresentation {

    @Operation(summary = "멤버 생성")
    fun signup(command: CreateMemberCommand): ApiResponse<String>
}
