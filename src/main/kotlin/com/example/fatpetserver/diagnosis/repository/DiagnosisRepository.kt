package com.example.fatpetserver.diagnosis.repository

import com.example.fatpetserver.diagnosis.entity.Diagnosis
import org.springframework.data.jpa.repository.JpaRepository

interface DiagnosisRepository : JpaRepository<Diagnosis, Long> {

    fun findAllByPetId(petId: Long): List<DiagnosisInfo>
}
