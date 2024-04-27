package com.example.fatpetserver.pet.service

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
    private val breedsQueryService: BreedsQueryService,
) {

    fun create(memberId: Long, command: CreatePetCommand): Pet {
        val (sex, name, species, breedsName, birthDate, neutered, feedCalories) = command

        val member = memberQueryService.getMemberByIdOrThrow(memberId)

        val breeds = breedsQueryService.getBreedsOrThrow(sex!!, species!!, breedsName)

        return petRepository.save(
            Pet(
                name = name,
                birthDate = birthDate!!,
                neutered = neutered!!,
                feedCalories = feedCalories,
                member = member,
                breeds = breeds,
            )
        )
    }

    fun update(id: Long, command: UpdatePetCommand) {
        val (newName, newNeutered, newFeedCalories) = command

        val updatedPet = petQueryService.getPetByIdOrThrow(id).apply {
            name = newName
            neutered = newNeutered!!
            feedCalories = newFeedCalories
        }

        petRepository.save(updatedPet)
    }

    fun delete(id: Long) =
        petQueryService.getPetByIdOrThrow(id).let { pet ->
            petRepository.delete(pet)
        }
}
