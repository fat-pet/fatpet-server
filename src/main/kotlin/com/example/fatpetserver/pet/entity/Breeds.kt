package com.example.fatpetserver.pet.entity

import com.example.fatpetserver.common.entity.BaseEntity
import com.example.fatpetserver.pet.enums.BreedsName
import com.example.fatpetserver.pet.enums.Sex
import com.example.fatpetserver.pet.enums.Species
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table


@Entity
@Table(name = "breeds")
class Breeds(
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val species: Species,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val name: BreedsName,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val sex: Sex,

    @Column
    val avgWeightLow: Float,

    @Column
    val avgWeightHigh: Float,
) : BaseEntity()
