package com.example.fatpetserver.member.service

import com.example.fatpetserver.member.repository.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberCommandServiceTest @Autowired constructor(
    private val memberRepository: MemberRepository,
    private val memberCommandService: MemberCommandService,
) {
//
//    @BeforeEach
//    fun setup() {
//        val memberEntity = Member(
//            email = TestMember.EMAIL,
//            loginId = TestMember.LOGIN_ID,
//            password = TestMember.PASSWORD,
//            nickname = TestMember.NICKNAME,
//        )
//
//        MEMBER = memberRepository.save(memberEntity)
//    }
//
//    @AfterEach
//    fun cleanup() {
//        memberRepository.deleteAll()
//    }
//
//    @Test
//    @DisplayName("회원가입 - 이미 사용 중인 아이디")
//    fun signupTest1() {
//        // given
//        val command = SignupCommand(
//            email = "new_email@email.com",
//            loginId = TestMember.LOGIN_ID,
//            password = "new_password",
//            nickname = "new_nickname",
//        )
//
//        // when
//        val throwable = catchThrowable { memberCommandService.signup(command) }
//
//        // then
//        assertThat(throwable).isInstanceOf(IllegalArgumentException::class.java)
//    }
//
//    @Test
//    @DisplayName("회원가입 - 이미 사용 중인 닉네임")
//    fun signupTest2() {
//        // given
//        val command = SignupCommand(
//            email = "new_email@email.com",
//            loginId = "new_loginId",
//            password = "new_password",
//            nickname = TestMember.NICKNAME,
//        )
//
//        // when
//        val throwable = catchThrowable { memberCommandService.signup(command) }
//
//        // then
//        assertThat(throwable).isInstanceOf(IllegalArgumentException::class.java)
//    }
//
//    @Test
//    @DisplayName("회원가입 - 정상적인 입력")
//    fun signupTest3() {
//        // given
//        val command = SignupCommand(
//            email = "new_email@email.com",
//            loginId = "new_loginId",
//            password = "new_password",
//            nickname = "new_nickname",
//        )
//
//        // when
//        val result = memberCommandService.signup(command)
//
//        // then
//        assertThat(result).isInstanceOf(Member::class.java)
//        assertThat(result.email).isEqualTo(command.email)
//    }
//
//    @Test
//    @DisplayName("회원 탈퇴 - 존재하지 않는 사용자")
//    fun deleteTest1() {
//        // given
//        val id = 0L
//
//        // when
//        val throwable = catchThrowable { memberCommandService.delete(id) }
//
//        // then
//        assertThat(throwable).isInstanceOf(IllegalArgumentException::class.java)
//        assertThat(throwable.message).isEqualTo("존재하지 않는 사용자입니다.")
//    }
//
//    @Test
//    @DisplayName("회원 탈퇴 - 정상적인 입력")
//    fun deleteTest2() {
//        // given
//        val id = MEMBER.id
//
//        // when
//        val result = runCatching { memberCommandService.delete(id) }.isSuccess
//
//        // then
//        assertThat(result).isTrue()
//    }
//
//    @Test
//    @DisplayName("회원 정보 수정 - 존재하지 않는 사용자")
//    fun updateTest1() {
//        // given
//        val id = 0L
//
//        val command = UpdateMemberCommand(
//            email = "new_email@email.com",
//            nickname = "new_nickname"
//        )
//
//        // when
//        val throwable = catchThrowable { memberCommandService.update(id, command) }
//
//        // then
//        assertThat(throwable).isInstanceOf(IllegalArgumentException::class.java)
//        assertThat(throwable.message).isEqualTo("존재하지 않는 사용자입니다.")
//    }
//
//    @Test
//    @DisplayName("회원 정보 수정 - 이미 사용 중인 닉네임")
//    fun updateTest2() {
//        // given
//        val id = MEMBER.id
//
//        val command = UpdateMemberCommand(
//            email = "new_email@email.com",
//            nickname = TestMember.NICKNAME
//        )
//
//        // when
//        val throwable = catchThrowable { memberCommandService.update(id, command) }
//
//        // then
//        assertThat(throwable).isInstanceOf(IllegalArgumentException::class.java)
//        assertThat(throwable.message).isEqualTo("이미 사용 중인 닉네임입니다.")
//    }
//
//
//    @Test
//    @DisplayName("회원 정보 수정 - 정상적인 입력")
//    fun updateTest3() {
//        // given
//        val id = MEMBER.id
//
//        val command = UpdateMemberCommand(
//            email = "new_email@email.com",
//            nickname = "new_nickname"
//        )
//
//        // when
//        val result = runCatching { memberCommandService.update(id, command) }.isSuccess
//
//        // then
//        assertThat(result).isTrue()
//    }
//
//    companion object {
//        private lateinit var MEMBER: Member
//    }
}
