package com.example.fatpetserver.breed.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

data class UpdateBreedCommand(
    @Schema(example = "1")
    @field:NotNull(message = "코드는 필수 입력값입니다.")
    val code: Int? = null,
    @Schema(example = "20")
    @field:Min(value = 0, message = "체중은 0보다 커야 합니다.")
    val avgWeightLow: Int = 0,
    @Schema(example = "30")
    @field:Min(value = 0, message = "체중은 0보다 커야 합니다.")
    val avgWeightHigh: Int = 0,
)
