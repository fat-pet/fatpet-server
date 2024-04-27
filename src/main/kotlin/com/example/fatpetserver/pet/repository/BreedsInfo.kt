package com.example.fatpetserver.pet.repository

import com.example.fatpetserver.pet.enums.Sex
import com.example.fatpetserver.pet.enums.Species

interface BreedsInfo {
    val species: Species
    val name: String
    val sex: Sex
    val avgWeightLow: Int
    val avgWeightHigh: Int
}
