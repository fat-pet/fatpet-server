package com.example.fatpetserver.breed.repository

import com.example.fatpetserver.breed.enums.Sex
import com.example.fatpetserver.breed.enums.Species

interface BreedInfo {
    val species: Species
    val name: String
    val nameKor: String
    val sex: Sex
    val avgWeightLow: Int
    val avgWeightHigh: Int
}
