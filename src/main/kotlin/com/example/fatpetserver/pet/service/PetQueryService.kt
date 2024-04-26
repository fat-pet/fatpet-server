package com.example.fatpetserver.pet.service

import com.example.fatpetserver.pet.dto.PetInfo
import com.example.fatpetserver.pet.entity.Pet
import com.example.fatpetserver.pet.repository.PetRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PetQueryService(
    private val petRepository: PetRepository,
) {

    fun getAllByMember(memberId: Long): List<PetInfo> =
        petRepository.findAllByMemberId(memberId)

    fun getPetByIdOrThrow(id: Long): Pet =
        petRepository.findByIdOrNull(id)
            ?: throw IllegalArgumentException("존재하지 않는 펫입니다.")
}
