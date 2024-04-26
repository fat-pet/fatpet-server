package com.example.fatpetserver.pet.dto

import com.example.fatpetserver.pet.enums.Sex
import com.example.fatpetserver.pet.enums.Species

data class BreedsInfo(
    val species: Species,

    val name: String,

    val sex: Sex,

    val avgWeightLow: Int,

    val avgWeightHigh: Int,
)
