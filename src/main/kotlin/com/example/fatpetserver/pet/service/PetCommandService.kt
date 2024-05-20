package com.example.fatpetserver.pet.service

import com.example.fatpetserver.breed.service.BreedQueryService
import com.example.fatpetserver.member.service.MemberQueryService
import com.example.fatpetserver.pet.dto.CreatePetCommand
import com.example.fatpetserver.pet.dto.UpdatePetCommand
import com.example.fatpetserver.pet.entity.Pet
import com.example.fatpetserver.pet.repository.PetRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PetCommandService(
    private val petRepository: PetRepository,
    private val petQueryService: PetQueryService,
    private val memberQueryService: MemberQueryService,
    private val breedQueryService: BreedQueryService,
) {

    fun create(memberId: Long, command: CreatePetCommand): Pet {
        val (sex, name, species, breedName, birthDate, neutered, feedCalories) = command

        val member = memberQueryService.getMemberByIdOrThrow(memberId)

        val breed = breedQueryService.getBreedOrThrow(sex!!, species!!, breedName)

        return petRepository.save(
            Pet(
                name = name,
                birthDate = birthDate!!,
                neutered = neutered!!,
                feedCalories = feedCalories,
                member = member,
                breed = breed,
            )
        )
    }

    fun update(petId: Long, command: UpdatePetCommand) {
        val (newName, newNeutered, newFeedCalories) = command

        val updatedPet = petQueryService.getPetByIdOrThrow(petId).apply {
            name = newName
            neutered = newNeutered!!
            feedCalories = newFeedCalories
        }

        petRepository.save(updatedPet)
    }

    fun delete(petId: Long) =
        petQueryService.getPetByIdOrThrow(petId).let { pet ->
            petRepository.delete(pet)
        }
}
