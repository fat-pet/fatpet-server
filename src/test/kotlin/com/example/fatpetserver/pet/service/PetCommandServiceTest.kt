package com.example.fatpetserver.pet.service

import com.example.fatpetserver.breeds.entity.Breeds
import com.example.fatpetserver.breeds.repository.BreedsRepository
import com.example.fatpetserver.member.TestMember
import com.example.fatpetserver.member.entity.Member
import com.example.fatpetserver.member.repository.MemberRepository
import com.example.fatpetserver.pet.dto.CreatePetCommand
import com.example.fatpetserver.pet.dto.UpdatePetCommand
import com.example.fatpetserver.pet.entity.Pet
import com.example.fatpetserver.pet.repository.PetRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PetCommandServiceTest @Autowired constructor(
    private val petRepository: PetRepository,
    private val petCommandService: PetCommandService,
) {

    @BeforeEach
    fun setup() {
        val petEntity = Pet(
            name = TestPet.NAME,
            birthDate = TestPet.BIRTH_DATE,
            neutered = TestPet.NEUTERED,
            feedCalories = TestPet.FEED_CALORIES,
            member = MEMBER,
            breeds = BREEDS,
        )

        PET_ID = petRepository.save(petEntity).id
    }

    @AfterEach
    fun cleanup() {
        petRepository.deleteAll()
    }

    @Test
    @DisplayName("펫 생성 - 존재하지 않는 품종")
    fun createTest1() {
        // given
        val memberId = MEMBER.id

        val command = CreatePetCommand(
            sex = TestPet.SEX,
            name = TestPet.NAME,
            species = TestPet.SPECIES,
            breedsName = "wrong_breeds",
            birthDate = TestPet.BIRTH_DATE,
            neutered = TestPet.NEUTERED,
            feedCalories = TestPet.FEED_CALORIES,
        )

        // when
        val throwable = catchThrowable { petCommandService.create(memberId, command) }

        // then
        assertThat(throwable).isInstanceOf(IllegalArgumentException::class.java)
        assertThat(throwable.message).isEqualTo("존재하지 않는 품종입니다.")
    }

    @Test
    @DisplayName("펫 생성 - 정상적인 입력")
    fun createTest2() {
        // given
        val memberId = MEMBER.id

        val command = CreatePetCommand(
            sex = TestPet.SEX,
            name = TestPet.NAME,
            species = TestPet.SPECIES,
            breedsName = TestPet.BREEDS_NAME,
            birthDate = TestPet.BIRTH_DATE,
            neutered = TestPet.NEUTERED,
            feedCalories = TestPet.FEED_CALORIES
        )

        // when
        val result = petCommandService.create(memberId, command)

        // then
        assertThat(result).isInstanceOf(Pet::class.java)
        assertThat(result.name).isEqualTo(TestPet.NAME)
    }

    @Test
    @DisplayName("펫 정보 수정 - 존재하지 않는 펫")
    fun updateTest1() {
        // given
        val id = 0L

        val command = UpdatePetCommand(
            name = TestPet.NAME,
            neutered = TestPet.NEUTERED,
            feedCalories = TestPet.FEED_CALORIES
        )

        // when
        val throwable = catchThrowable { petCommandService.update(id, command) }

        // then
        assertThat(throwable).isInstanceOf(IllegalArgumentException::class.java)
        assertThat(throwable.message).isEqualTo("존재하지 않는 펫입니다.")
    }

    @Test
    @DisplayName("펫 정보 수정 - 정상적인 입력")
    fun updateTest2() {
        // given
        val id = PET_ID

        val command = UpdatePetCommand(
            name = TestPet.NAME,
            neutered = TestPet.NEUTERED,
            feedCalories = TestPet.FEED_CALORIES
        )

        // when
        val result = runCatching { petCommandService.update(id, command) }.isSuccess

        // then
        assertThat(result).isTrue()
    }

    @Test
    @DisplayName("펫 삭제 - 존재하지 않는 펫")
    fun deleteTest1() {
        // given
        val id = 0L

        // when
        val throwable = catchThrowable { petCommandService.delete(id) }

        // then
        assertThat(throwable).isInstanceOf(IllegalArgumentException::class.java)
        assertThat(throwable.message).isEqualTo("존재하지 않는 펫입니다.")
    }

    @Test
    @DisplayName("펫 삭제 - 정상적인 입력")
    fun deleteTest2() {
        // given
        val id = PET_ID

        // when
        val result = runCatching { petCommandService.delete(id) }.isSuccess

        // then
        assertThat(result).isTrue()
    }

    companion object {
        private lateinit var MEMBER: Member
        private lateinit var BREEDS: Breeds
        private var PET_ID = 0L

        @JvmStatic
        @BeforeAll
        fun setup(
            @Autowired memberRepository: MemberRepository,
            @Autowired breedsRepository: BreedsRepository,
        ) {
            val memberEntity = Member(
                email = TestMember.EMAIL,
                loginId = TestMember.LOGIN_ID,
                password = TestMember.PASSWORD,
                nickname = TestMember.NICKNAME,
            )

            MEMBER = memberRepository.save(memberEntity)

            BREEDS = breedsRepository.findBySexAndSpeciesAndName(
                TestPet.SEX,
                TestPet.SPECIES,
                TestPet.BREEDS_NAME,
            )!!
        }

        @JvmStatic
        @AfterAll
        fun cleanup(@Autowired memberRepository: MemberRepository) {
            memberRepository.deleteAll()
        }
    }
}
