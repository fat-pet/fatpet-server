package com.example.fatpetserver.post.entity

import com.example.fatpetserver.common.entity.BaseEntity
import com.example.fatpetserver.member.entity.Member
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "post")
class Post(
    @Column(nullable = false)
    var title: String,

    @Column(nullable = false, columnDefinition = "VARCHAR(1023)")
    var content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, updatable = false)
    val member: Member,

    @OneToMany(
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
        fetch = FetchType.EAGER
    )
    @JoinColumn(name = "post_id")
    val comments: List<Comment> = listOf(),
) : BaseEntity()
