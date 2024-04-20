package com.example.fatpetserver.pet.service

import com.example.fatpetserver.pet.dto.CreatePetCommand
import com.example.fatpetserver.pet.entity.Pet
import com.example.fatpetserver.pet.repository.BreedsRepository
import com.example.fatpetserver.pet.repository.PetRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PetCommandService(
    private val breedsRepository: BreedsRepository,
    private val petRepository: PetRepository,
) {

    fun create(createPetCommand: CreatePetCommand): Pet {
        val (name, species, breedsName, birthDate, sex, isNeutered, feedAmount) = createPetCommand

        val breeds = breedsRepository.findBySpeciesAndNameAndSex(
            species = species!!,
            name = breedsName,
            sex = sex!!,
        ) ?: throw IllegalArgumentException("존재하지 않는 품종입니다.")

        return petRepository.save(
            Pet(
                name = name,
                birthDate = birthDate!!,
                isNeutered = isNeutered!!,
                feedAmount = feedAmount,
                breeds = breeds
            )
        )
    }
}
