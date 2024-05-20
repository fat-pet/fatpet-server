package com.example.fatpetserver.admin.controller

import com.example.fatpetserver.admin.dto.SignupAdminCommand
import com.example.fatpetserver.breeds.dto.CreateBreedsCommand
import com.example.fatpetserver.breeds.dto.UpdateBreedsCommand
import com.example.fatpetserver.breeds.entity.Breeds
import com.example.fatpetserver.common.dto.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "Admin API")
interface AdminApi {

    @Operation(summary = "어드민 회원가입")
    fun signup(command: SignupAdminCommand)

    @Operation(summary = "품종 목록 조회")
    fun getAll(): ApiResponse<List<Breeds>>

    @Operation(summary = "품종 생성")
    fun create(command: CreateBreedsCommand)

    @Operation(summary = "품종 정보 수정")
    fun update(breedsId: Long, command: UpdateBreedsCommand)

    @Operation(summary = "품종 삭제")
    fun delete(breedsId: Long)
}
