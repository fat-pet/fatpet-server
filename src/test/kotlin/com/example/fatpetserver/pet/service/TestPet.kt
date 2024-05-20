package com.example.fatpetserver.pet.service

import com.example.fatpetserver.breed.enums.Sex
import com.example.fatpetserver.breed.enums.Species
import java.time.YearMonth

object TestPet {
    val SEX = Sex.MALE
    const val NAME = "NAME"
    val SPECIES = Species.DOG
    const val BREED_NAME = "BEA"
    val BIRTH_DATE = YearMonth.now()!!
    const val NEUTERED = false
    const val FEED_CALORIES = 450
}
