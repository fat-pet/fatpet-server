package com.example.fatpetserver.pet.entity

import com.example.fatpetserver.breed.entity.Breed
import com.example.fatpetserver.common.entity.BaseEntity
import com.example.fatpetserver.common.entity.YearMonthConverter
import com.example.fatpetserver.diagnosis.entity.Diagnosis
import com.example.fatpetserver.diagnosis.enums.Bcs
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

    @Column(nullable = false, name = "is_neutered")
    var neutered: Boolean,

    @Column(nullable = false)
    var feedCalories: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    val member: Member,

    @ManyToOne
    @JoinColumn(name = "breed_id", nullable = false, updatable = false)
    val breed: Breed,

    @OneToMany(
        mappedBy = "pet",
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
        fetch = FetchType.LAZY,
    )
    val diagnosises: List<Diagnosis> = listOf(),
) : BaseEntity() {

    private val period: Period
        get() = Period.between(
            LocalDate.of(birthDate.year, birthDate.month, 1),
            LocalDate.now()
        )

    val ageInMonth: Int
        get() = period.years * 12 + period.months

    val age: Int
        get() = period.years

    private fun getRer(weight: Float): Float = weight * 30 + 70

    fun getDer(weight: Float, bcs: Bcs): Float {
        val rer = getRer(weight)

        if (ageInMonth < 4) {
            return rer * 3F
        }

        if (ageInMonth < 18) {
            return rer * 2F
        }

        if (bcs == Bcs.OVER) {
            return rer * 1.2F
        }

        return if (neutered) {
            rer * 1.6F
        } else {
            rer * 1.8F
        }
    }
}
