package com.example.fatpetserver.post.service

import com.example.fatpetserver.post.entity.Comment
import com.example.fatpetserver.post.repository.CommentRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CommentQueryService(
    private val commentRepository: CommentRepository,
) {
    fun getCommentByIdOrThrow(commentId: Long): Comment =
        commentRepository.findByIdOrNull(commentId)
            ?: throw IllegalArgumentException("존재하지 않는 댓글입니다.")
}
