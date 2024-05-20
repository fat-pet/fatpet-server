package com.example.fatpetserver.breeds.repository

import com.example.fatpetserver.breeds.enums.Sex
import com.example.fatpetserver.breeds.enums.Species

interface BreedsInfo {
    val species: Species
    val name: String
    val sex: Sex
    val avgWeightLow: Int
    val avgWeightHigh: Int
}
