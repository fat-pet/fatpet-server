package com.example.fatpetserver.pet.service

import com.example.fatpetserver.member.TestMember
import com.example.fatpetserver.member.entity.Member
import com.example.fatpetserver.member.repository.MemberRepository
import com.example.fatpetserver.pet.entity.Pet
import com.example.fatpetserver.pet.repository.BreedsRepository
import com.example.fatpetserver.pet.repository.PetRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PetQueryServiceTest @Autowired constructor(
    private val memberRepository: MemberRepository,
    private val breedsRepository: BreedsRepository,
    private val petRepository: PetRepository,
    private val petQueryService: PetQueryService,
) {

    @AfterEach
    fun cleanup() {
        memberRepository.deleteAll()
    }

    @Test
    @DisplayName("내 펫 목록 조회")
    fun getAllTest1() {
        // given
        val memberEntity = Member(
            email = TestMember.EMAIL,
            loginId = TestMember.LOGIN_ID,
            password = TestMember.PASSWORD,
            nickname = TestMember.NICKNAME,
        )

        val member = memberRepository.save(memberEntity)

        val memberId = member.id

        val breeds = breedsRepository.findBySexAndSpeciesAndName(
            TestPet.SEX,
            TestPet.SPECIES,
            TestPet.BREEDS_NAME,
        )

        val pet = Pet(
            name = TestPet.NAME,
            birthDate = TestPet.BIRTH_DATE,
            isNeutered = TestPet.IS_NEUTERED,
            feedCalories = TestPet.FEED_CALORIES,
            member = member,
            breeds = breeds!!,
        )

        petRepository.save(pet)

        // when
        val result = petQueryService.getAll(memberId)

        // then
        assertThat(result.size).isEqualTo(1)
        assertThat(result[0].name).isEqualTo(TestPet.NAME)
    }
}
