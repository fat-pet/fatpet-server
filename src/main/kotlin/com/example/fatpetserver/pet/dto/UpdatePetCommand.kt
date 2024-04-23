package com.example.fatpetserver.pet.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length

data class UpdatePetCommand(
    @Schema(example = "name")
    @field:NotBlank(message = "이름은 필수 입력값입니다.")
    @field:Length(min = 1, max = 8, message = "이름은 1글자 이상, 8글자 이하여야 합니다.")
    val name: String = "",

    @Schema(example = "true")
    @field:NotBlank(message = "중성화 여부는 필수 입력값입니다.")
    val isNeutered: Boolean? = null,

    @Schema(example = "300")
    @field:Min(value = 1, message = "사료 열량은 0보다 커야 합니다.")
    val feedCalories: Int = 0,
)
