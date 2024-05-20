package com.example.fatpetserver.pet.controller

import com.example.fatpetserver.common.auth.UserDetails
import com.example.fatpetserver.common.dto.ApiResponse
import com.example.fatpetserver.pet.dto.CreatePetCommand
import com.example.fatpetserver.pet.dto.UpdatePetCommand
import com.example.fatpetserver.pet.repository.PetInfo
import com.example.fatpetserver.pet.service.PetCommandService
import com.example.fatpetserver.pet.service.PetQueryService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
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
@RequestMapping("/api/pets")
class PetController(
    private val petCommandService: PetCommandService,
    private val petQueryService: PetQueryService,
) : PetApi {

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    override fun getAllByMember(@AuthenticationPrincipal userDetails: UserDetails): ApiResponse<List<PetInfo>> {
        return ApiResponse.success(petQueryService.getAllByMember(userDetails.id))
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    override fun create(
        @AuthenticationPrincipal userDetails: UserDetails,
        @Valid @RequestBody command: CreatePetCommand,
    ) {
        petCommandService.create(userDetails.id, command)
    }

    @PutMapping("/{petId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    override fun update(
        @PathVariable petId: Long,
        @Valid @RequestBody command: UpdatePetCommand,
    ) {
        petCommandService.update(petId, command)
    }

    @DeleteMapping("/{petId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    override fun delete(@PathVariable petId: Long) {
        petCommandService.delete(petId)
    }
}
