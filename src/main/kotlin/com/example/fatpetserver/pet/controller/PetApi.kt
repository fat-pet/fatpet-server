package com.example.fatpetserver.pet.controller

import com.example.fatpetserver.common.ApiResponse
import com.example.fatpetserver.common.auth.UserDetails
import com.example.fatpetserver.pet.dto.CreatePetCommand
import com.example.fatpetserver.pet.dto.UpdatePetCommand
import com.example.fatpetserver.pet.entity.Pet
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "Pet API")
interface PetApi {

    @Operation(summary = "내 펫 목록 조회")
    fun getAll(userDetails: UserDetails): ApiResponse<List<Pet>>

    @Operation(summary = "펫 생성")
    fun create(userDetails: UserDetails, command: CreatePetCommand)

    @Operation(summary = "펫 정보 수정")
    fun update(id: Long, command: UpdatePetCommand)

    @Operation(summary = "펫 삭제")
    fun delete(id: Long)
}
