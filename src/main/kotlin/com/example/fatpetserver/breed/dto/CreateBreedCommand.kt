package com.example.fatpetserver.breed.dto

import com.example.fatpetserver.breed.enums.Sex
import com.example.fatpetserver.breed.enums.Species
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreateBreedCommand(
    @Schema(example = "DOG")
    @field:NotNull(message = "종은 필수 입력값입니다.")
    val species: Species,
    @Schema(example = "BEA")
    @field:NotBlank(message = "품종은 필수 입력값입니다.")
    val name: String,
    @Schema(example = "비글")
    @field:NotBlank(message = "한글명 품종은 필수 입력값입니다.")
    val nameKor: String,
    @Schema(example = "1")
    @field:NotNull(message = "코드는 필수 입력값입니다.")
    val code: Int? = null,
    @Schema(example = "MALE")
    @field:NotNull(message = "성별은 필수 입력값입니다.")
    val sex: Sex? = null,
    @Schema(example = "20")
    @field:Min(value = 0, message = "체중은 0보다 커야 합니다.")
    val avgWeightLow: Int = 0,
    @Schema(example = "30")
    @field:Min(value = 0, message = "체중은 0보다 커야 합니다.")
    val avgWeightHigh: Int = 0,
)
