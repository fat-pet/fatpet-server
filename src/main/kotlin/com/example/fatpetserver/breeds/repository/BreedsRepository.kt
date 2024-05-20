package com.example.fatpetserver.breeds.repository

import com.example.fatpetserver.breeds.entity.Breeds
import com.example.fatpetserver.breeds.enums.Sex
import com.example.fatpetserver.breeds.enums.Species
import org.springframework.data.jpa.repository.JpaRepository

interface BreedsRepository : JpaRepository<Breeds, Long> {

    fun findBySexAndSpeciesAndName(
        sex: Sex,
        species: Species,
        name: String,
    ): Breeds?
}
