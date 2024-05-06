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
@Table(name = "comment")
class Comment(
    @Column(nullable = false)
    var content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    val post: Post,

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = true, updatable = false)
    val member: Member,

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = true, updatable = false)
    val parent: Comment?,

    @OneToMany(
        mappedBy = "parent",
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
        fetch = FetchType.EAGER
    )
    val children: MutableList<Comment> = mutableListOf(),
) : BaseEntity() {

    fun delete() {
        parent?.children.let { child ->
            child?.removeIf { it == this }
        }

        children.clear()
    }
}
