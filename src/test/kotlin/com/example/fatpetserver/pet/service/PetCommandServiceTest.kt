package com.example.fatpetserver.pet.service

import com.example.fatpetserver.member.TestMember
import com.example.fatpetserver.member.entity.Member
import com.example.fatpetserver.member.repository.MemberRepository
import com.example.fatpetserver.pet.dto.CreatePetCommand
import com.example.fatpetserver.pet.dto.UpdatePetCommand
import com.example.fatpetserver.pet.entity.Pet
import com.example.fatpetserver.pet.repository.BreedsRepository
import com.example.fatpetserver.pet.repository.PetRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PetCommandServiceTest @Autowired constructor(
    private val memberRepository: MemberRepository,
    private val breedsRepository: BreedsRepository,
    private val petRepository: PetRepository,
    private val petCommandService: PetCommandService,
) {

    @AfterEach
    fun cleanup() {
        memberRepository.deleteAll()
    }

    @Test
    @DisplayName("펫 생성 - 존재하지 않는 품종")
    fun createTest1() {
        // given
        val member = Member(
            email = TestMember.EMAIL,
            loginId = TestMember.LOGIN_ID,
            password = TestMember.PASSWORD,
            nickname = TestMember.NICKNAME,
        )

        val memberId = memberRepository.save(member).id

        val command = CreatePetCommand(
            name = TestPet.NAME,
            species = TestPet.SPECIES,
            breedsName = "wrong_breeds",
            birthDate = TestPet.BIRTH_DATE,
            sex = TestPet.SEX,
            isNeutered = TestPet.IS_NEUTERED,
            feedCalories = TestPet.FEED_CALORIES
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
        val member = Member(
            email = TestMember.EMAIL,
            loginId = TestMember.LOGIN_ID,
            password = TestMember.PASSWORD,
            nickname = TestMember.NICKNAME,
        )

        val memberId = memberRepository.save(member).id

        val command = CreatePetCommand(
            sex = TestPet.SEX,
            name = TestPet.NAME,
            species = TestPet.SPECIES,
            breedsName = TestPet.BREEDS_NAME,
            birthDate = TestPet.BIRTH_DATE,
            isNeutered = TestPet.IS_NEUTERED,
            feedCalories = TestPet.FEED_CALORIES,
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
            isNeutered = TestPet.IS_NEUTERED,
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
        val memberEntity = Member(
            email = TestMember.EMAIL,
            loginId = TestMember.LOGIN_ID,
            password = TestMember.PASSWORD,
            nickname = TestMember.NICKNAME,
        )

        val member = memberRepository.save(memberEntity)

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

        val id = petRepository.save(pet).id

        val command = UpdatePetCommand(
            name = TestPet.NAME,
            isNeutered = TestPet.IS_NEUTERED,
            feedCalories = TestPet.FEED_CALORIES
        )

        // when
        val result = runCatching { petCommandService.delete(id) }.isSuccess

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
        val memberEntity = Member(
            email = TestMember.EMAIL,
            loginId = TestMember.LOGIN_ID,
            password = TestMember.PASSWORD,
            nickname = TestMember.NICKNAME,
        )

        val member = memberRepository.save(memberEntity)

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

        val id = petRepository.save(pet).id

        // when
        val result = runCatching { petCommandService.delete(id) }.isSuccess

        // then
        assertThat(result).isTrue()
    }
}
