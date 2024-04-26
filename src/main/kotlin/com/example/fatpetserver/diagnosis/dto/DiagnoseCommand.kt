package com.example.fatpetserver.diagnosis.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min

data class DiagnoseCommand(
    @Schema(example = "5.5")
    @field:Min(value = 1, message = "체중은 0보다 커야 합니다.")
    val weight: Float = 0F,

    @Schema(example = "13")
    @field:Min(value = 1, message = "목 둘레는 0보다 커야 합니다.")
    val neckCirc: Float = 0F,

    @Schema(example = "25")
    @field:Min(value = 1, message = "가슴 둘레는 0보다 커야 합니다.")
    val chestCirc: Float = 0F,

    @Schema(example = "150")
    @field:Min(value = 1, message = "사료 급여량은 0보다 커야 합니다.")
    val feedAmount: Int = 0,
)
