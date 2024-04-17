package com.example.fatpetserver.member.service

import com.example.fatpetserver.member.dto.SigninRequest
import com.example.fatpetserver.member.dto.SigninResponse
import com.example.fatpetserver.member.dto.SignupRequest
import com.example.fatpetserver.member.entity.Member
import com.example.fatpetserver.member.repository.MemberRepository
import org.apache.coyote.BadRequestException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberAuthServiceTest @Autowired constructor(
    private val memberAuthService: MemberAuthService,
) {

    @Test
    @DisplayName("회웝가입: 이미 사용 중인 아이디")
    fun signupTest1() {
        // given
        val request = SignupRequest(
            email = "new_email@email.com",
            loginId = LOGIN_ID,
            password = "new_password",
            nickname = "new_nickname",
        )

        // when
        val throwable = catchThrowable { memberAuthService.signup(request) }

        // then
        assertThat(throwable).isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    @DisplayName("회원가입: 이미 사용 중인 닉네임")
    fun signupTest2() {
        // given
        val request = SignupRequest(
            email = "new_email@email.com",
            loginId = "new_loginId",
            password = "new_password",
            nickname = NICKNAME,
        )

        // when
        val throwable = catchThrowable { memberAuthService.signup(request) }

        // then
        assertThat(throwable).isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    @DisplayName("회원가입: 성공")
    fun signupTest3() {
        // given
        val request = SignupRequest(
            email = "new_email@email.com",
            loginId = "new_loginId",
            password = "new_password",
            nickname = "new_nickname",
        )

        // when
        val result = memberAuthService.signup(request)

        // then
        assertThat(result).isInstanceOf(Member::class.java)
        assertThat(result.email).isEqualTo(request.email)
    }

    @Test
    @DisplayName("로그인: 아이디 불일치")
    fun signinTest1() {
        //given
        val request = SigninRequest(
            loginId = "wrong_loginId",
            password = PASSWORD,
        )

        // when
        val throwable = catchThrowable { memberAuthService.signin(request) }

        // then
        assertThat(throwable).isInstanceOf(BadRequestException::class.java)
    }

    @Test
    @DisplayName("로그인: 비밀번호 불일치")
    fun signinTest2() {
        //given
        val request = SigninRequest(
            loginId = LOGIN_ID,
            password = "wrong_password",
        )

        // when
        val throwable = catchThrowable { memberAuthService.signin(request) }

        // then
        assertThat(throwable).isInstanceOf(BadRequestException::class.java)
    }

    @Test
    @DisplayName("로그인: 성공")
    fun signinTest3() {
        //given
        val request = SigninRequest(
            loginId = LOGIN_ID,
            password = PASSWORD,
        )

        // when
        val result = memberAuthService.signin(request)

        // then
        assertThat(result).isInstanceOf(SigninResponse::class.java)
        assertThat(result.token).isNotBlank()
    }

    companion object {
        private const val EMAIL = "email@email.com"
        private const val LOGIN_ID = "loginId"
        private const val PASSWORD = "password"
        private const val NICKNAME = "nickname"

        @JvmStatic
        @BeforeAll
        fun setup(
            @Autowired repository: MemberRepository,
            @Autowired passwordEncoder: PasswordEncoder,
        ) {
            val member = Member(
                email = EMAIL,
                loginId = LOGIN_ID,
                password = passwordEncoder.encode(PASSWORD),
                nickname = NICKNAME,
            )

            repository.save(member)
        }
    }
}
