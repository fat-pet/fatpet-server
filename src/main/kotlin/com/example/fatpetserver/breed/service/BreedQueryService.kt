package com.example.fatpetserver.breed.service

import com.example.fatpetserver.breed.entity.Breed
import com.example.fatpetserver.breed.enums.Sex
import com.example.fatpetserver.breed.enums.Species
import com.example.fatpetserver.breed.repository.BreedRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BreedQueryService(
    private val breedRepository: BreedRepository,
) {
    fun getAll(): List<Breed> = breedRepository.findAll()

    fun getBreedByIdOrThrow(breedId: Long): Breed =
        breedRepository.findByIdOrNull(breedId)
            ?: throw IllegalArgumentException("존재하지 않는 품종입니다.")

    fun getBreedOrThrow(
        sex: Sex,
        species: Species,
        breedsName: String,
    ): Breed =
        breedRepository.findBySexAndSpeciesAndName(sex, species, breedsName)
            ?: throw IllegalArgumentException("존재하지 않는 품종입니다.")
}
