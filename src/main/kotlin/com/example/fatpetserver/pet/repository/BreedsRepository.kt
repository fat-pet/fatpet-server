package com.example.fatpetserver.pet.repository

import com.example.fatpetserver.pet.entity.Breeds
import com.example.fatpetserver.pet.enums.Sex
import com.example.fatpetserver.pet.enums.Species
import org.springframework.data.jpa.repository.JpaRepository

interface BreedsRepository : JpaRepository<Breeds, Long> {

    fun findBySpeciesAndNameAndSex(
        species: Species,
        name: String,
        sex: Sex,
    ): Breeds?
}
