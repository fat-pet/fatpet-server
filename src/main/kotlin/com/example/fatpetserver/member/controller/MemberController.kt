package com.example.fatpetserver.member.controller

import com.example.fatpetserver.common.ApiResponse
import com.example.fatpetserver.common.auth.UserDetails
import com.example.fatpetserver.member.dto.SigninRequest
import com.example.fatpetserver.member.dto.SigninResponse
import com.example.fatpetserver.member.dto.SignupRequest
import com.example.fatpetserver.member.dto.UpdateMemberCommand
import com.example.fatpetserver.member.service.MemberAuthService
import com.example.fatpetserver.member.service.MemberCommandService
import com.example.fatpetserver.member.service.MemberQueryService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
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
    override fun signup(@Valid @RequestBody command: SignupRequest) {
        memberAuthService.signup(command)
    }

    @PostMapping("/signin")
    @ResponseStatus(value = HttpStatus.OK)
    override fun signin(@Valid @RequestBody query: SigninRequest): ApiResponse<SigninResponse> {
        return ApiResponse.success(memberAuthService.signin(query))
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    override fun delete(@AuthenticationPrincipal userDetails: UserDetails) {
        memberCommandService.delete(userDetails.id)
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    override fun update(
        @AuthenticationPrincipal userDetails: UserDetails,
        @Valid @RequestBody command: UpdateMemberCommand,
    ) {
        memberCommandService.update(
            id = userDetails.id,
            command = command
        )
    }

    @GetMapping("/check")
    @ResponseStatus(value = HttpStatus.OK)
    override fun checkDuplicate(
        @RequestParam(required = false) loginId: String?,
        @RequestParam(required = false) nickname: String?,
    ): ApiResponse<Boolean> {
        return ApiResponse.success(
            memberQueryService.checkDuplicate(
                loginId = loginId,
                nickname = nickname,
            )
        )
    }
}
