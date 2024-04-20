package com.example.fatpetserver.pet.entity

import com.example.fatpetserver.common.YearMonthConverter
import com.example.fatpetserver.common.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.YearMonth


@Entity
@Table(name = "Pet")
class Pet(
    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    @Convert(converter = YearMonthConverter::class)
    val birthDate: YearMonth,

    @Column(nullable = false)
    val isNeutered: Boolean,

    @Column(nullable = false)
    val feedAmount: Int,

    @Column(nullable = false)
    val feedCount: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "breed_id", nullable = false)
    val breed: Breed,
) : BaseEntity()
