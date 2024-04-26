package com.example.fatpetserver.pet.dto

import java.time.YearMonth

data class PetInfo(
    val id: Long,
    
    var name: String,

    val breeds: BreedsInfo,

    val birthDate: YearMonth,

    var isNeutered: Boolean,

    var feedCalories: Int,
)
