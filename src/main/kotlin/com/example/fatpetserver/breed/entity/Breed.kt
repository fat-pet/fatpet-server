package com.example.fatpetserver.breed.entity

import com.example.fatpetserver.breed.enums.Sex
import com.example.fatpetserver.breed.enums.Species
import com.example.fatpetserver.common.entity.IdEntity
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
    var code: Int,

    @Column
    @Enumerated(EnumType.STRING)
    val sex: Sex,

    @Column
    var avgWeightLow: Int,

    @Column
    var avgWeightHigh: Int,
) : IdEntity()
