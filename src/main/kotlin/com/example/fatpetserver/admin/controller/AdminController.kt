package com.example.fatpetserver.admin.controller

import com.example.fatpetserver.admin.dto.SignupAdminCommand
import com.example.fatpetserver.admin.service.AdminCommandService
import com.example.fatpetserver.breeds.dto.CreateBreedsCommand
import com.example.fatpetserver.breeds.dto.UpdateBreedsCommand
import com.example.fatpetserver.breeds.entity.Breeds
import com.example.fatpetserver.breeds.service.BreedsCommandService
import com.example.fatpetserver.breeds.service.BreedsQueryService
import com.example.fatpetserver.common.dto.ApiResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/admin")
class AdminController(
    private val adminCommandService: AdminCommandService,
    private val breedsCommandService: BreedsCommandService,
    private val breedsQueryService: BreedsQueryService,
) : AdminApi {

    @PostMapping("/signup")
    @ResponseStatus(value = HttpStatus.CREATED)
    override fun signup(@Valid @RequestBody command: SignupAdminCommand) {
        adminCommandService.signup(command)
    }

    @GetMapping("/breeds")
    @ResponseStatus(value = HttpStatus.OK)
    override fun getAll(): ApiResponse<List<Breeds>> {
        return ApiResponse.success(breedsQueryService.getAll())
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    override fun create(@Valid @RequestBody command: CreateBreedsCommand) {
        breedsCommandService.create(command)
    }

    @PutMapping("/breeds/{breedsId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    override fun update(
        @PathVariable breedsId: Long,
        @Valid @RequestBody command: UpdateBreedsCommand,
    ) {
        breedsCommandService.update(breedsId, command)
    }

    @DeleteMapping("/breeds/{breedsId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    override fun delete(@PathVariable breedsId: Long) {
        breedsCommandService.delete(breedsId)
    }
}
