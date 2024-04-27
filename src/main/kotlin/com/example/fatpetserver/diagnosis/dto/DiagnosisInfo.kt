package com.example.fatpetserver.diagnosis.dto

import com.example.fatpetserver.diagnosis.enums.Bcs
import java.time.LocalDateTime

interface DiagnosisInfo {
    val id: Long
    val weight: Float
    val neckCirc: Float
    val chestCirc: Float
    val feedAmount: Int
    val bcs: Bcs
    val der: Float
    val createdDate: LocalDateTime
}
