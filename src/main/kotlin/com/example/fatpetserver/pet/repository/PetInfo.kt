package com.example.fatpetserver.pet.repository

import com.example.fatpetserver.breed.repository.BreedInfo
import java.time.YearMonth

interface PetInfo {
    val id: Long
    val name: String
    val breed: BreedInfo
    val birthDate: YearMonth
    val neutered: Boolean
    val feedCalories: Int
}
