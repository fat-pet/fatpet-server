package com.example.fatpetserver.diagnosis.service

import com.example.fatpetserver.diagnosis.entity.Diagnosis
import com.example.fatpetserver.diagnosis.repository.DiagnosisRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class DiagnosisQueryService(
    private val diagnosisRepository: DiagnosisRepository,
) {

    fun getAll(petId: Long): List<Diagnosis> = diagnosisRepository.findAllByPetId(petId)

    fun getDiagnosisByIdOrThrow(id: Long): Diagnosis =
        diagnosisRepository.findByIdOrNull(id)
            ?: throw IllegalArgumentException("존재하지 않는 진단입니다.")
}
