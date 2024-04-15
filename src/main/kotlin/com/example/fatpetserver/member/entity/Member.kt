package com.example.fatpetserver.member.entity

import com.example.fatpetserver.common.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table

@Entity
@Table(name = "member")
class Member(
    @Column(nullable = false)
    var email: String,

    @Column(nullable = false, unique = true)
    var loginId: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false, unique = true)
    var nickname: String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val role: Role = Role.MEMBER,
) : BaseEntity()
