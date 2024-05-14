package com.example.fatpetserver.diagnosis.service

import com.example.fatpetserver.diagnosis.dto.CreateDiagnosisCommand
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

    fun diagnose(command: CreateDiagnosisCommand) {
        val (petId, weight, neckCirc, chestCirc, feedAmount) = command

        val pet = petQueryService.getPetByIdOrThrow(petId)

        val breeds = pet.breeds

        val bcs = diagnosisQueryService.predictBcs(
            age = pet.age,
            weight = weight,
            neckCirc = neckCirc,
            chestCirc = chestCirc,
            feedAmount = feedAmount,
            breedsCode = breeds.code,
            speciesCode = breeds.species.value
        )

        val der = pet.getDer(weight, bcs)

        diagnosisRepository.save(
            Diagnosis(
                weight = weight,
                neckCirc = neckCirc,
                chestCirc = chestCirc,
                feedAmount = feedAmount,
                bcs = bcs,
                der = der,
                pet = pet,
            )
        )
    }

    fun delete(diagnosisId: Long) =
        diagnosisQueryService.getDiagnosisByIdOrThrow(diagnosisId).let { diagnosis ->
            diagnosisRepository.delete(diagnosis)
        }
}
