package com.example.fatpetserver.diagnosis.entity

import com.example.fatpetserver.common.entity.BaseEntity
import com.example.fatpetserver.diagnosis.enums.Bcs
import com.example.fatpetserver.pet.entity.Pet
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "diagnosis")
class Diagnosis(
    @Column(nullable = false)
    val weight: Float,

    @Column(nullable = false)
    val neckCirc: Float,

    @Column(nullable = false)
    val chestCirc: Float,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val bcs: Bcs,

    @Column(nullable = false)
    val der: Float,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false, updatable = false)
    val pet: Pet,
) : BaseEntity()
