package com.example.fatpetserver.pet.service

import com.example.fatpetserver.pet.enums.Sex
import com.example.fatpetserver.pet.enums.Species
import java.time.YearMonth

object TestPet {
    const val NAME = "NAME"
    val SPECIES = Species.DOG
    const val BREEDS_NAME = "BEA"
    val BIRTH_DATE = YearMonth.now()!!
    val SEX = Sex.MALE
    const val IS_NEUTERED = false
    const val FEED_CALORIES = 450
}
