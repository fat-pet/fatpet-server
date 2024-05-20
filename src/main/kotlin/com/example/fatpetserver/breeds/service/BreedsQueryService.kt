package com.example.fatpetserver.breeds.service

import com.example.fatpetserver.breeds.entity.Breeds
import com.example.fatpetserver.breeds.enums.Sex
import com.example.fatpetserver.breeds.enums.Species
import com.example.fatpetserver.breeds.repository.BreedsRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BreedsQueryService(
    private val breedsRepository: BreedsRepository,
) {

    fun getAll(): List<Breeds> = breedsRepository.findAll()

    fun getBreedsByIdOrThrow(breedsId: Long): Breeds =
        breedsRepository.findByIdOrNull(breedsId)
            ?: throw IllegalArgumentException("존재하지 않는 품종입니다.")

    fun getBreedsOrThrow(sex: Sex, species: Species, breedsName: String): Breeds =
        breedsRepository.findBySexAndSpeciesAndName(sex, species, breedsName)
            ?: throw IllegalArgumentException("존재하지 않는 품종입니다.")
}
