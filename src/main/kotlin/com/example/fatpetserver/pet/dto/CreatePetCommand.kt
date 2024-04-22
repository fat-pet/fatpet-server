package com.example.fatpetserver.pet.dto

import com.example.fatpetserver.pet.enums.BreedsName
import com.example.fatpetserver.pet.enums.Sex
import com.example.fatpetserver.pet.enums.Species
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length
import java.time.YearMonth

data class CreatePetCommand(
    @Schema(example = "MALE")
    @field:NotBlank(message = "성별은 필수 입력값입니다.")
    val sex: Sex? = null,

    @Schema(example = "name")
    @field:NotBlank(message = "이름은 필수 입력값입니다.")
    @field:Length(min = 1, max = 8, message = "이름은 1글자 이상 8글자 이하여야 합니다.")
    val name: String = "",

    @Schema(example = "DOG")
    @field:NotBlank(message = "종은 필수 입력값입니다.")
    val species: Species? = null,

    @Schema(example = "BEA")
    @field:NotBlank(message = "품종은 필수 입력값입니다.")
    val breedsName: String = "",

    @Schema(example = "2024-04")
    @field:NotBlank(message = "출생일은 필수 입력값입니다.")
    val birthDate: YearMonth? = null,

    @Schema(example = "true")
    @field:NotBlank(message = "중성화 여부는 필수 입력값입니다.")
    val isNeutered: Boolean? = null,

    @Schema(example = "300")
    @field:Min(value = 1, message = "사료 급여량은 0보다 커야 합니다.")
    val feedAmount: Int = 0,
)
