package com.example.fatpetserver.breeds.service

import com.example.fatpetserver.breeds.dto.CreateBreedsCommand
import com.example.fatpetserver.breeds.dto.UpdateBreedsCommand
import com.example.fatpetserver.breeds.entity.Breeds
import com.example.fatpetserver.breeds.repository.BreedsRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BreedsCommandService(
    private val breedsRepository: BreedsRepository,
    private val breedsQueryService: BreedsQueryService,
) {

    fun create(command: CreateBreedsCommand): Breeds {
        val (species, name, code, sex, avgWeightLow, avgWeightHigh) = command

        return breedsRepository.save(
            Breeds(
                species = species,
                name = name,
                code = code!!,
                sex = sex!!,
                avgWeightLow = avgWeightLow,
                avgWeightHigh = avgWeightHigh
            )
        )
    }


    fun update(breedsId: Long, command: UpdateBreedsCommand) {
        val (newCode, newAvgWeightLow, newAvgWeightHigh) = command

        val updatedBreeds = breedsQueryService.getBreedsByIdOrThrow(breedsId).apply {
            code = newCode!!
            avgWeightLow = newAvgWeightLow
            avgWeightHigh = newAvgWeightHigh
        }

        breedsRepository.save(updatedBreeds)
    }

    fun delete(breedsId: Long) =
        breedsQueryService.getBreedsByIdOrThrow(breedsId).let { breeds ->
            breedsRepository.delete(breeds)
        }
}
