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

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberCommandServiceTest @Autowired constructor(
    private val memberRepository: MemberRepository,
    private val memberCommandService: MemberCommandService,
) {

    @AfterEach
    fun cleanup() {
        memberRepository.deleteAll()
    }

    @Test
    @DisplayName("회원 탈퇴 - 존재하지 않는 사용자")
    fun deleteTest1() {
        // given
        val id = 0L

        // when
        val throwable = catchThrowable { memberCommandService.delete(id) }

        // then
        assertThat(throwable).isInstanceOf(IllegalArgumentException::class.java)
        assertThat(throwable.message).isEqualTo("존재하지 않는 사용자입니다.")
    }

    @Test
    @DisplayName("회원 탈퇴 - 정상적인 입력")
    fun deleteTest2() {
        // given
        val member = Member(
            email = TestMember.EMAIL,
            loginId = TestMember.LOGIN_ID,
            password = TestMember.PASSWORD,
            nickname = TestMember.NICKNAME,
        )

        val memberId = memberRepository.save(member).id

        // when
        val result = runCatching { memberCommandService.delete(memberId) }.isSuccess

        // then
        assertThat(result).isTrue()
    }

    @Test
    @DisplayName("회원 정보 수정 - 존재하지 않는 사용자")
    fun updateTest1() {
        // given
        val id = 0L
        val command = UpdateMemberCommand(
            email = "new_email@email.com",
            nickname = "new_nickname"
        )

        // when
        val throwable = catchThrowable { memberCommandService.update(id, command) }

        // then
        assertThat(throwable).isInstanceOf(IllegalArgumentException::class.java)
        assertThat(throwable.message).isEqualTo("존재하지 않는 사용자입니다.")
    }

    @Test
    @DisplayName("회원 정보 수정 - 이미 사용 중인 닉네임")
    fun updateTest2() {
        // given
        val member = Member(
            email = TestMember.EMAIL,
            loginId = TestMember.LOGIN_ID,
            password = TestMember.PASSWORD,
            nickname = TestMember.NICKNAME,
        )

        val memberId = memberRepository.save(member).id

        val command = UpdateMemberCommand(
            email = "new_email@email.com",
            nickname = TestMember.NICKNAME
        )

        // when
        val throwable = catchThrowable { memberCommandService.update(memberId, command) }

        // then
        assertThat(throwable).isInstanceOf(IllegalArgumentException::class.java)
        assertThat(throwable.message).isEqualTo("이미 사용 중인 닉네임입니다.")
    }


    @Test
    @DisplayName("회원 정보 수정 - 정상적인 입력")
    fun updateTest3() {
        // given
        val member = Member(
            email = TestMember.EMAIL,
            loginId = TestMember.LOGIN_ID,
            password = TestMember.PASSWORD,
            nickname = TestMember.NICKNAME,
        )

        val memberId = memberRepository.save(member).id

        val command = UpdateMemberCommand(
            email = "new_email@email.com",
            nickname = "new_nickname"
        )

        // when
        val result = runCatching { memberCommandService.update(memberId, command) }.isSuccess

        // then
        assertThat(result).isTrue()
    }
}
