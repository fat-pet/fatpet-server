package com.example.fatpetserver.post.repository

import com.example.fatpetserver.post.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {

    fun findAllByPostIdAndParentIdIsNull(postId: Long): List<CommentInfo>
}
