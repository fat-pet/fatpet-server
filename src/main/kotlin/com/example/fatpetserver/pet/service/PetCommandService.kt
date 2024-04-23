package com.example.fatpetserver.pet.service

import com.example.fatpetserver.member.repository.MemberRepository
import com.example.fatpetserver.pet.dto.CreatePetCommand
import com.example.fatpetserver.pet.dto.UpdatePetCommand
import com.example.fatpetserver.pet.entity.Pet
import com.example.fatpetserver.pet.repository.BreedsRepository
import com.example.fatpetserver.pet.repository.PetRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PetCommandService(
    private val memberRepository: MemberRepository,
    private val breedsRepository: BreedsRepository,
    private val petRepository: PetRepository,
) {

    fun create(memberId: Long, command: CreatePetCommand): Pet {
        val (sex, name, species, breedsName, birthDate, isNeutered, feedCalories) = command

        val member = memberRepository.findByIdOrNull(memberId)
            ?: throw IllegalArgumentException("존재하지 않는 사용자입니다.")

        val breeds = breedsRepository.findBySexAndSpeciesAndName(sex!!, species!!, breedsName)
            ?: throw IllegalArgumentException("존재하지 않는 품종입니다.")

        return petRepository.save(
            Pet(
                name = name,
                birthDate = birthDate!!,
                isNeutered = isNeutered!!,
                feedCalories = feedCalories,
                member = member,
                breeds = breeds,
            )
        )
    }

    fun update(id: Long, command: UpdatePetCommand) {
        val (newName, newIsNeutered, newFeedCalories) = command

        val updatedPet = petRepository.findByIdOrNull(id)?.apply {
            name = newName
            isNeutered = newIsNeutered!!
            feedCalories = newFeedCalories
        } ?: throw IllegalArgumentException("존재하지 않는 펫입니다.")

        petRepository.save(updatedPet)
    }

    fun delete(id: Long) {
        petRepository.findByIdOrNull(id)?.let { pet ->
            petRepository.delete(pet)
        } ?: throw IllegalArgumentException("존재하지 않는 펫입니다.")
    }
}
