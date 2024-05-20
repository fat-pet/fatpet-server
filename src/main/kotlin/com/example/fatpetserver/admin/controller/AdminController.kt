package com.example.fatpetserver.admin.controller

import com.example.fatpetserver.admin.dto.SignupAdminCommand
import com.example.fatpetserver.admin.service.AdminCommandService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/admin")
class AdminController(
    private val adminCommandService: AdminCommandService,
) : AdminApi {

    @PostMapping("/signup")
    @ResponseStatus(value = HttpStatus.CREATED)
    override fun signup(@Valid @RequestBody command: SignupAdminCommand) {
        adminCommandService.signup(command)
    }
}
