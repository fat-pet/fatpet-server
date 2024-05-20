package com.example.fatpetserver.pet.repository

import com.example.fatpetserver.breeds.repository.BreedsInfo
import java.time.YearMonth

interface PetInfo {
    val id: Long
    val name: String
    val breeds: BreedsInfo
    val birthDate: YearMonth
    val neutered: Boolean
    val feedCalories: Int
}
