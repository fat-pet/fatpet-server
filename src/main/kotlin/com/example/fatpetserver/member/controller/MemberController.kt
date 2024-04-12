package com.example.fatpetserver.member.controller

import com.example.fatpetserver.common.ApiResponse
import com.example.fatpetserver.member.dto.CreateMemberCommand
import com.example.fatpetserver.member.service.MemberCommandService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/members")
class MemberController(
    private val memberCommandService: MemberCommandService,
) : MemberApiPresentation {

    @PostMapping("/signup")
    @ResponseStatus(value = HttpStatus.CREATED)
    override fun signup(@RequestBody command: CreateMemberCommand): ApiResponse<String> {
        memberCommandService.create(command)

        return ApiResponse.success("created")
    }
}
