package com.example.fatpetserver.member.service

import com.example.fatpetserver.member.TestMember
import com.example.fatpetserver.member.dto.UpdateMemberCommand
import com.example.fatpetserver.member.entity.Member
import com.example.fatpetserver.member.repository.MemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberCommandServiceTest @Autowired constructor(
    private val repository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
    private val memberCommandService: MemberCommandService,
) {

    @AfterEach
    fun cleanup() {
        repository.deleteAll()
    }

    @Test
    @DisplayName("회원 삭제 - 존재하지 않는 사용자")
    fun deleteTest1() {
        // given
        val id = 1L

        // when
        val throwable = catchThrowable { memberCommandService.delete(id) }

        // then
        assertThat(throwable).isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    @DisplayName("회원 삭제 - 정상적인 입력")
    fun deleteTest2() {
        // given
        val member = Member(
            email = TestMember.EMAIL,
            loginId = TestMember.LOGIN_ID,
            password = passwordEncoder.encode(TestMember.PASSWORD),
            nickname = TestMember.NICKNAME,
        )

        repository.save(member)

        // when
        val result = runCatching { memberCommandService.delete(member.id) }.isSuccess

        // then
        assertThat(result).isTrue()
    }

    @Test
    @DisplayName("회원 정보 수정 - 존재하지 않는 사용자")
    fun updateTest1() {
        // given
        val id = 1L
        val command = UpdateMemberCommand(
            email = "new_email@email.com",
            nickname = "new_nickname"
        )

        // when
        val throwable = catchThrowable { memberCommandService.update(id, command) }

        // then
        assertThat(throwable).isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    @DisplayName("회원 정보 수정 - 이미 사용 중인 닉네임")
    fun updateTest2() {
        // given
        val member = Member(
            email = TestMember.EMAIL,
            loginId = TestMember.LOGIN_ID,
            password = passwordEncoder.encode(TestMember.PASSWORD),
            nickname = TestMember.NICKNAME,
        )

        repository.save(member)

        val command = UpdateMemberCommand(
            email = "new_email@email.com",
            nickname = TestMember.NICKNAME
        )

        // when
        val throwable = catchThrowable { memberCommandService.update(member.id, command) }

        // then
        assertThat(throwable).isInstanceOf(IllegalArgumentException::class.java)
    }


    @Test
    @DisplayName("회원 정보 수정 - 정상적인 입력")
    fun updateTest3() {
        // given
        val member = Member(
            email = TestMember.EMAIL,
            loginId = TestMember.LOGIN_ID,
            password = passwordEncoder.encode(TestMember.PASSWORD),
            nickname = TestMember.NICKNAME,
        )

        repository.save(member)

        val command = UpdateMemberCommand(
            email = "new_email@email.com",
            nickname = "new_nickname"
        )

        // when
        val result = runCatching { memberCommandService.update(member.id, command) }.isSuccess

        // then
        assertThat(result).isTrue()
    }
}
