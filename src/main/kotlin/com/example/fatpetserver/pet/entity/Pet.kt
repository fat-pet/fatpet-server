package com.example.fatpetserver.pet.entity

import com.example.fatpetserver.common.entity.BaseEntity
import com.example.fatpetserver.common.entity.YearMonthConverter
import com.example.fatpetserver.diagnosis.entity.Diagnosis
import com.example.fatpetserver.member.entity.Member
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.LocalDate
import java.time.Period
import java.time.YearMonth

@Entity
@Table(name = "pet")
class Pet(
    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    @Convert(converter = YearMonthConverter::class)
    val birthDate: YearMonth,

    @Column(nullable = false)
    var neutered: Boolean,

    @Column(nullable = false)
    var feedCalories: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    val member: Member,

    @ManyToOne
    @JoinColumn(name = "breeds_id", nullable = false, updatable = false)
    val breeds: Breeds,

    @OneToMany(
        mappedBy = "pet",
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
        fetch = FetchType.LAZY,
    )
    val diagnosises: List<Diagnosis> = listOf(),
) : BaseEntity() {

    val age: Int
        get() = Period.between(
            LocalDate.of(birthDate.year, birthDate.month.value, 1),
            LocalDate.now()
        ).years
}
