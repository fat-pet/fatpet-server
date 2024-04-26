package com.example.fatpetserver.diagnosis.service

import com.example.fatpetserver.diagnosis.entity.Diagnosis
import com.example.fatpetserver.diagnosis.repository.DiagnosisRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class DiagnosisQueryService(
    private val diagnosisRepository: DiagnosisRepository,
) {

    fun getAll(petId: Long): List<Diagnosis> = diagnosisRepository.findAllByPetId(petId)
}
