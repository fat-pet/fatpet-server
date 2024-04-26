package com.example.fatpetserver.pet.service

import com.example.fatpetserver.pet.entity.Breeds
import com.example.fatpetserver.pet.enums.Sex
import com.example.fatpetserver.pet.enums.Species
import com.example.fatpetserver.pet.repository.BreedsRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BreedsQueryService(
    private val breedsRepository: BreedsRepository,
) {

    fun getBreedsOrThrow(sex: Sex, species: Species, breedsName: String): Breeds =
        breedsRepository.findBySexAndSpeciesAndName(sex, species, breedsName)
            ?: throw IllegalArgumentException("존재하지 않는 품종입니다.")
}
