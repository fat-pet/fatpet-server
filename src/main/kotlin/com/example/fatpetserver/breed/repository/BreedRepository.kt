package com.example.fatpetserver.breed.repository

import com.example.fatpetserver.breed.entity.Breed
import com.example.fatpetserver.breed.enums.Sex
import com.example.fatpetserver.breed.enums.Species
import org.springframework.data.jpa.repository.JpaRepository

interface BreedRepository : JpaRepository<Breed, Long> {
    fun findBySexAndSpeciesAndName(
        sex: Sex,
        species: Species,
        name: String,
    ): Breed?
}
