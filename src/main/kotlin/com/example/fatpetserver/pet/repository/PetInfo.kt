package com.example.fatpetserver.pet.repository

import java.time.YearMonth

interface PetInfo {
    val id: Long
    val name: String
    val breeds: BreedsInfo
    val birthDate: YearMonth
    val neutered: Boolean
    val feedCalories: Int
}
