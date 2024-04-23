package com.example.fatpetserver.member.entity

import com.example.fatpetserver.common.entity.BaseEntity
import com.example.fatpetserver.member.enums.Role
import com.example.fatpetserver.pet.entity.Pet
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "member")
class Member(
    @Column(nullable = false)
    var email: String,

    @Column(nullable = false, unique = true)
    val loginId: String,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false, unique = true)
    var nickname: String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val role: Role = Role.MEMBER,

    @OneToMany(mappedBy = "member", cascade = [CascadeType.ALL], orphanRemoval = true)
    val pets: MutableList<Pet> = mutableListOf(),
) : BaseEntity()
