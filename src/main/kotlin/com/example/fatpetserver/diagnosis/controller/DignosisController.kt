package com.example.fatpetserver.diagnosis.controller

import com.example.fatpetserver.common.dto.ApiResponse
import com.example.fatpetserver.diagnosis.dto.CreateDiagnosisCommand
import com.example.fatpetserver.diagnosis.dto.DiagnoseResponse
import com.example.fatpetserver.diagnosis.repository.DiagnosisInfo
import com.example.fatpetserver.diagnosis.service.DiagnosisCommandService
import com.example.fatpetserver.diagnosis.service.DiagnosisQueryService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/diagnoses")
class DignosisController(
    private val diagnosisCommandService: DiagnosisCommandService,
    private val diagnosisQueryService: DiagnosisQueryService,
) : DiagnosisApi {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    override fun getAllByPet(
        @RequestParam petId: Long,
    ): ApiResponse<List<DiagnosisInfo>> = ApiResponse.success(diagnosisQueryService.getAllByPet(petId))

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    override fun diagnose(
        @Valid @RequestBody command: CreateDiagnosisCommand,
    ): ApiResponse<DiagnoseResponse> = ApiResponse.success(diagnosisCommandService.diagnose(command))

    @DeleteMapping("/{diagnosisId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    override fun delete(
        @PathVariable diagnosisId: Long,
    ) {
        diagnosisCommandService.delete(diagnosisId)
    }

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    fun test(): String = diagnosisQueryService.apiTest()
}
