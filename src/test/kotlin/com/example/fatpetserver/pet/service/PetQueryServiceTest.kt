package com.example.fatpetserver.pet.service

import com.example.fatpetserver.member.TestMember
import com.example.fatpetserver.member.entity.Member
import com.example.fatpetserver.member.repository.MemberRepository
import com.example.fatpetserver.pet.entity.Pet
import com.example.fatpetserver.pet.repository.BreedsRepository
import com.example.fatpetserver.pet.repository.PetRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PetQueryServiceTest @Autowired constructor(
    private val petQueryService: PetQueryService,
) {

    @Test
    @DisplayName("내 펫 목록 조회")
    fun getAllTest1() {
        // given
        val memberId = MEMBER.id

        // when
        val result = petQueryService.getAllByMember(memberId)

        // then
        assertThat(result.size).isEqualTo(1)
        assertThat(result[0].name).isEqualTo(TestPet.NAME)
    }

    companion object {
        private lateinit var MEMBER: Member
        private var PET_ID = 0L

        @JvmStatic
        @BeforeAll
        fun setup(
            @Autowired memberRepository: MemberRepository,
            @Autowired breedsRepository: BreedsRepository,
            @Autowired petRepository: PetRepository,
        ) {
            val memberEntity = Member(
                email = TestMember.EMAIL,
                loginId = TestMember.LOGIN_ID,
                password = TestMember.PASSWORD,
                nickname = TestMember.NICKNAME,
            )

            MEMBER = memberRepository.save(memberEntity)

            val breeds = breedsRepository.findBySexAndSpeciesAndName(
                TestPet.SEX,
                TestPet.SPECIES,
                TestPet.BREEDS_NAME,
            )

            val petEntity = Pet(
                name = TestPet.NAME,
                birthDate = TestPet.BIRTH_DATE,
                isNeutered = TestPet.IS_NEUTERED,
                feedCalories = TestPet.FEED_CALORIES,
                member = MEMBER,
                breeds = breeds!!,
            )

            PET_ID = petRepository.save(petEntity).id
        }

        @JvmStatic
        @AfterAll
        fun cleanup(@Autowired memberRepository: MemberRepository) {
            memberRepository.deleteAll()
        }
    }
}
