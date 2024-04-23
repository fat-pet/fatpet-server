package com.example.fatpetserver.pet.entity

import com.example.fatpetserver.common.entity.BaseEntity
import com.example.fatpetserver.common.entity.YearMonthConverter
import com.example.fatpetserver.member.entity.Member
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.YearMonth

@Entity
@Table(name = "Pet")
class Pet(
    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    @Convert(converter = YearMonthConverter::class)
    val birthDate: YearMonth,

    @Column(nullable = false)
    var isNeutered: Boolean,

    @Column(nullable = false)
    var feedCalories: Int,

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    val member: Member,

    @ManyToOne
    @JoinColumn(name = "breeds_id", nullable = true)
    val breeds: Breeds,
) : BaseEntity()
