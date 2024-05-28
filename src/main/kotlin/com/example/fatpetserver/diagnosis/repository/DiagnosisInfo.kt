package com.example.fatpetserver.diagnosis.repository

import com.example.fatpetserver.diagnosis.enums.Bcs
import java.time.LocalDateTime

interface DiagnosisInfo {
    val id: Long
    val weight: Float
    val neckCirc: Float
    val chestCirc: Float
    val bcs: Bcs
    val der: Float
    val createdDate: LocalDateTime
}
