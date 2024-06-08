package com.example.fatpetserver.pet.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PetQueryServiceTest
    @Autowired
    constructor(
        private val petQueryService: PetQueryService,
    ) {
//
//    @Test
//    @DisplayName("내 펫 목록 조회")
//    fun getAllTest1() {
//        // given
//        val memberId = MEMBER.id
//
//        // when
//        val result = petQueryService.getAllByMember(memberId)
//
//        // then
//        assertThat(result.size).isEqualTo(1)
//        assertThat(result[0].name).isEqualTo(TestPet.NAME)
//    }
//
//    companion object {
//        private lateinit var MEMBER: Member
//        private var PET_ID = 0L
//
//        @JvmStatic
//        @BeforeAll
//        fun setup(
//            @Autowired memberRepository: MemberRepository,
//            @Autowired breedRepository: BreedRepository,
//            @Autowired petRepository: PetRepository,
//        ) {
//            val memberEntity = Member(
//                email = TestMember.EMAIL,
//                loginId = TestMember.LOGIN_ID,
//                password = TestMember.PASSWORD,
//                nickname = TestMember.NICKNAME,
//            )
//
//            MEMBER = memberRepository.save(memberEntity)
//
//            val breed = breedRepository.findBySexAndSpeciesAndName(
//                TestPet.SEX,
//                TestPet.SPECIES,
//                TestPet.BREED_NAME,
//            )
//
//            val petEntity = Pet(
//                name = TestPet.NAME,
//                birthDate = TestPet.BIRTH_DATE,
//                neutered = TestPet.NEUTERED,
//                feedCalories = TestPet.FEED_CALORIES,
//                member = MEMBER,
//                breed = breed!!,
//            )
//
//            PET_ID = petRepository.save(petEntity).id
//        }
//
//        @JvmStatic
//        @AfterAll
//        fun cleanup(@Autowired memberRepository: MemberRepository) {
//            memberRepository.deleteAll()
//        }
//    }
    }
