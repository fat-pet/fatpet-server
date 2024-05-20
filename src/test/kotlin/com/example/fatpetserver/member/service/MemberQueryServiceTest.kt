package com.example.fatpetserver.member.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberQueryServiceTest @Autowired constructor(
    private val memberQueryService: MemberQueryService,
) {
//
//    @Test
//    @DisplayName("로그인 - 아이디 불일치")
//    fun signinTest1() {
//        //given
//        val query = SigninQuery(
//            loginId = "wrong_loginId",
//            password = TestMember.PASSWORD,
//        )
//
//        // when
//        val throwable = catchThrowable { memberQueryService.signin(query) }
//
//        // then
//        assertThat(throwable).isInstanceOf(IllegalArgumentException::class.java)
//    }
//
//    @Test
//    @DisplayName("로그인 - 비밀번호 불일치")
//    fun signinTest2() {
//        //given
//        val query = SigninQuery(
//            loginId = TestMember.LOGIN_ID,
//            password = "wrong_password",
//        )
//
//        // when
//        val throwable = catchThrowable { memberQueryService.signin(query) }
//
//        // then
//        assertThat(throwable).isInstanceOf(IllegalArgumentException::class.java)
//    }
//
//    @Test
//    @DisplayName("로그인 - 정상적인 입력")
//    fun signinTest3() {
//        //given
//        val query = SigninQuery(
//            loginId = TestMember.LOGIN_ID,
//            password = TestMember.PASSWORD,
//        )
//
//        // when
//        val result = memberQueryService.signin(query)
//
//        // then
//        assertThat(result).isInstanceOf(SigninResponse::class.java)
//        assertThat(result.token).isNotBlank()
//    }
//
//    companion object {
//        @JvmStatic
//        @BeforeAll
//        fun setup(
//            @Autowired memberRepository: MemberRepository,
//            @Autowired passwordEncoder: PasswordEncoder,
//        ) {
//            val memberEntity = Member(
//                email = TestMember.EMAIL,
//                loginId = TestMember.LOGIN_ID,
//                password = passwordEncoder.encode(TestMember.PASSWORD),
//                nickname = TestMember.NICKNAME,
//            )
//
//            memberRepository.save(memberEntity)
//        }
//
//        @JvmStatic
//        @AfterAll
//        fun cleanup(@Autowired memberRepository: MemberRepository) {
//            memberRepository.deleteAll()
//        }
//    }
}
