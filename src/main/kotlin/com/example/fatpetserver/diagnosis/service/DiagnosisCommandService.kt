package com.example.fatpetserver.diagnosis.service

import com.example.fatpetserver.breed.enums.Sex
import com.example.fatpetserver.diagnosis.dto.CreateDiagnosisCommand
import com.example.fatpetserver.diagnosis.dto.DiagnoseResponse
import com.example.fatpetserver.diagnosis.entity.Diagnosis
import com.example.fatpetserver.diagnosis.repository.DiagnosisRepository
import com.example.fatpetserver.pet.service.PetQueryService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class DiagnosisCommandService(
    private val diagnosisRepository: DiagnosisRepository,
    private val diagnosisQueryService: DiagnosisQueryService,
    private val petQueryService: PetQueryService,
) {
    fun diagnose(command: CreateDiagnosisCommand): DiagnoseResponse {
        val (petId, weight, neckCirc, chestCirc) = command

        val pet = petQueryService.getPetByIdOrThrow(petId)
        val breed = pet.breed

        val sex =
            if (pet.neutered) {
                if (breed.sex == Sex.MALE) {
                    0
                } else {
                    3
                }
            } else {
                if (breed.sex == Sex.MALE) {
                    2
                } else {
                    1
                }
            }

        val bcs =
            diagnosisQueryService.predictBcs(
                age = pet.age,
                sex = sex,
                weight = weight,
                neckCirc = neckCirc,
                chestCirc = chestCirc,
                breedCode = breed.code,
                speciesCode = breed.species.code,
            )

        val der = pet.getDer(weight, bcs)

        val gptSolution =
            diagnosisQueryService.getGptSolution(
                pet = pet,
                breed = breed,
                weight = weight,
                neckCirc = neckCirc,
                chestCirc = chestCirc,
                bcs = bcs,
                der = der,
            )

        diagnosisRepository.save(
            Diagnosis(
                weight = weight,
                neckCirc = neckCirc,
                chestCirc = chestCirc,
                bcs = bcs,
                der = der,
                pet = pet,
            ),
        )

        return DiagnoseResponse(
            avgWeightLow = breed.avgWeightLow,
            avgWeightHigh = breed.avgWeightHigh,
            bcs = bcs,
            der = der,
            gptSolution = gptSolution,
        )
    }

    fun delete(diagnosisId: Long) =
        diagnosisQueryService.getDiagnosisByIdOrThrow(diagnosisId).let { diagnosis ->
            diagnosisRepository.delete(diagnosis)
        }
}
