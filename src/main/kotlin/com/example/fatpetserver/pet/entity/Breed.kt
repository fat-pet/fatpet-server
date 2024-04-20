package com.example.fatpetserver.pet.entity

import com.example.fatpetserver.common.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table


@Entity
@Table(name = "breed")
class Breed(
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val species: Species,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val sex: Sex,

    @Column
    val avgWeightLow: Float,

    @Column
    val avgWeightHigh: Float,
) : BaseEntity()
