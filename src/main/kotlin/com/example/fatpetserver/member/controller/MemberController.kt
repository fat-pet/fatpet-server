package com.example.fatpetserver.member.controller

import com.example.fatpetserver.common.ApiResponse
import com.example.fatpetserver.common.auth.UserDetails
import com.example.fatpetserver.member.dto.SigninQuery
import com.example.fatpetserver.member.dto.SigninResponse
import com.example.fatpetserver.member.dto.SignupCommand
import com.example.fatpetserver.member.service.MemberAuthService
import com.example.fatpetserver.member.service.MemberCommandService
import com.example.fatpetserver.member.service.MemberQueryService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/members")
class MemberController(
    private val memberAuthService: MemberAuthService,
    private val memberCommandService: MemberCommandService,
    private val memberQueryService: MemberQueryService,
) : MemberApiPresentation {

    @PostMapping("/signup")
    @ResponseStatus(value = HttpStatus.CREATED)
    override fun signup(@Valid @RequestBody command: SignupCommand): ApiResponse<String> {
        memberAuthService.signup(command)

        return ApiResponse.success("회원가입 완료")
    }

    @PostMapping("/signin")
    @ResponseStatus(value = HttpStatus.OK)
    override fun signin(@Valid @RequestBody query: SigninQuery): ApiResponse<SigninResponse> {
        return ApiResponse.success(memberAuthService.signin(query))
    }

    @GetMapping("/test")
    @ResponseStatus(value = HttpStatus.OK)
    fun test(@AuthenticationPrincipal userDetails: UserDetails): ApiResponse<String> {
        println(userDetails.toString())
        return ApiResponse.success("test")
    }
}
