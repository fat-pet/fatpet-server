package com.example.fatpetserver.diagnosis.controller

import com.example.fatpetserver.common.dto.ApiResponse
import com.example.fatpetserver.diagnosis.dto.CreateDiagnosisCommand
import com.example.fatpetserver.diagnosis.dto.DiagnoseResponse
import com.example.fatpetserver.diagnosis.repository.DiagnosisInfo
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "Diagnosis API")
interface DiagnosisApi {
    @Operation(summary = "펫 진단 목록 조회")
    fun getAllByPet(petId: Long): ApiResponse<List<DiagnosisInfo>>

    @Operation(summary = "비만도 진단")
    fun diagnose(command: CreateDiagnosisCommand): ApiResponse<DiagnoseResponse>

    @Operation(summary = "진단 삭제")
    fun delete(diagnosisId: Long)
}
