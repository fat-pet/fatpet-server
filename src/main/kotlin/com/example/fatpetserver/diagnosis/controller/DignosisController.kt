package com.example.fatpetserver.diagnosis.controller

import com.example.fatpetserver.common.ApiResponse
import com.example.fatpetserver.diagnosis.dto.CreateDiagnosisCommand
import com.example.fatpetserver.diagnosis.entity.Diagnosis
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
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/diagnoses")
class DignosisController(
    private val diagnosisCommandService: DiagnosisCommandService,
    private val diagnosisQueryService: DiagnosisQueryService,
) : DiagnosisApi {

    @GetMapping("/{petId}")
    @ResponseStatus(HttpStatus.OK)
    override fun getAll(@PathVariable petId: Long): ApiResponse<List<Diagnosis>> {
        return ApiResponse.success(diagnosisQueryService.getAll(petId))
    }

    @PostMapping("/{petId}")
    @ResponseStatus(HttpStatus.CREATED)
    override fun create(
        @PathVariable petId: Long,
        @Valid @RequestBody command: CreateDiagnosisCommand,
    ) {
        diagnosisCommandService.create(petId, command)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    override fun delete(@PathVariable id: Long) {
        diagnosisCommandService.delete(id)
    }
}
