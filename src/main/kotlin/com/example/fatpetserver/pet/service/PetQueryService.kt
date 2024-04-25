package com.example.fatpetserver.pet.service

import com.example.fatpetserver.pet.entity.Pet
import com.example.fatpetserver.pet.repository.PetRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PetQueryService(
    private val petRepository: PetRepository,
) {

    fun getAll(memberId: Long): List<Pet> = petRepository.findAllByMemberId(memberId)
}
