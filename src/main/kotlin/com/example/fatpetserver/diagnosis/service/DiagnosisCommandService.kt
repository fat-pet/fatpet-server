package com.example.fatpetserver.diagnosis.service

import com.example.fatpetserver.diagnosis.dto.CreateDiagnosisCommand
import com.example.fatpetserver.diagnosis.entity.Diagnosis
import com.example.fatpetserver.diagnosis.enums.Bcs
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

    fun create(petId: Long, command: CreateDiagnosisCommand) {
        val (weight, neckCirc, chestCirc, feedAmount) = command

        val pet = petQueryService.getPetByIdOrThrow(petId)

        val result = diagnoseObsity()

        val bcs = Bcs.IDEAL
        val der = 1000F

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

    fun diagnoseObsity() {
        // TODO
    }

    fun delete(id: Long) =
        diagnosisQueryService.getDiagnosisByIdOrThrow(id).let { diagnosis ->
            diagnosisRepository.delete(diagnosis)
        }
}
