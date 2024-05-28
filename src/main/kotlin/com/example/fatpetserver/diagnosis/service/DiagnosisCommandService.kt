package com.example.fatpetserver.diagnosis.service

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

        val bcs = diagnosisQueryService.predictBcs(
            age = pet.age,
            weight = weight,
            neckCirc = neckCirc,
            chestCirc = chestCirc,
            breedsCode = breed.code,
            speciesCode = breed.species.code,
        )

        val der = pet.getDer(weight, bcs)

        val gptSolution = diagnosisQueryService.getGptSolution(
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
            )
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
