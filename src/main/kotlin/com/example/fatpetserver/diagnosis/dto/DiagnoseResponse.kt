package com.example.fatpetserver.diagnosis.dto

import com.example.fatpetserver.diagnosis.enums.Bcs
import io.swagger.v3.oas.annotations.media.Schema

data class DiagnoseResponse(
    @Schema(example = "25")
    val avgWeightLow: Int,

    @Schema(example = "35")
    val avgWeightHigh: Int,

    @Schema(example = "IDEAL")
    val bcs: Bcs,

    @Schema(example = "253.0")
    val der: Float,

    @Schema(example = "...")
    val gptSolution: String,
)
