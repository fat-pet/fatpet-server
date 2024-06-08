package com.example.fatpetserver.breed.controller

import com.example.fatpetserver.breed.dto.CreateBreedCommand
import com.example.fatpetserver.breed.dto.UpdateBreedCommand
import com.example.fatpetserver.breed.entity.Breed
import com.example.fatpetserver.common.dto.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "Breed API")
interface BreedApi {
    @Operation(summary = "품종 목록 조회")
    fun getAll(): ApiResponse<List<Breed>>

    @Operation(summary = "품종 생성")
    fun create(command: CreateBreedCommand)

    @Operation(summary = "품종 정보 수정")
    fun update(
        breedId: Long,
        command: UpdateBreedCommand,
    )

    @Operation(summary = "품종 삭제")
    fun delete(breedId: Long)
}
