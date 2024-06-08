package com.example.fatpetserver.breed.service

import com.example.fatpetserver.breed.dto.CreateBreedCommand
import com.example.fatpetserver.breed.dto.UpdateBreedCommand
import com.example.fatpetserver.breed.entity.Breed
import com.example.fatpetserver.breed.repository.BreedRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BreedCommandService(
    private val breedRepository: BreedRepository,
    private val breedsQueryService: BreedQueryService,
) {
    fun create(command: CreateBreedCommand): Breed {
        val (species, name, nameKor, code, sex, avgWeightLow, avgWeightHigh) = command

        return breedRepository.save(
            Breed(
                species = species,
                name = name,
                nameKor = nameKor,
                code = code!!,
                sex = sex!!,
                avgWeightLow = avgWeightLow,
                avgWeightHigh = avgWeightHigh,
            ),
        )
    }

    fun update(
        breedId: Long,
        command: UpdateBreedCommand,
    ) {
        val (newCode, newAvgWeightLow, newAvgWeightHigh) = command

        val updatedBreeds =
            breedsQueryService.getBreedByIdOrThrow(breedId).apply {
                code = newCode!!
                avgWeightLow = newAvgWeightLow
                avgWeightHigh = newAvgWeightHigh
            }

        breedRepository.save(updatedBreeds)
    }

    fun delete(breedId: Long) =
        breedsQueryService.getBreedByIdOrThrow(breedId).let { breeds ->
            breedRepository.delete(breeds)
        }
}
