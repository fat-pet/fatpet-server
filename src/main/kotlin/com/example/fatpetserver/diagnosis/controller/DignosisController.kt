package com.example.fatpetserver.diagnosis.controller

import com.example.fatpetserver.diagnosis.service.DiagnosisCommandService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/diagnoses")
class DignosisController(
    private val diagnosisCommandService: DiagnosisCommandService,
) : DiagnosisApi
