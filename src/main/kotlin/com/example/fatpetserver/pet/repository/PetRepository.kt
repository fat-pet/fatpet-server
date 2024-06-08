package com.example.fatpetserver.pet.repository

import com.example.fatpetserver.pet.entity.Pet
import org.springframework.data.jpa.repository.JpaRepository

interface PetRepository : JpaRepository<Pet, Long> {
    fun findAllByMemberId(memberId: Long): List<PetInfo>
}
