package com.example.fatpetserver.post.service

import com.example.fatpetserver.member.service.MemberQueryService
import com.example.fatpetserver.post.dto.CreateCommentCommand
import com.example.fatpetserver.post.entity.Comment
import com.example.fatpetserver.post.repository.CommentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CommentCommandService(
    private val commentRepository: CommentRepository,
    private val commentQueryService: CommentQueryService,
    private val postQueryService: PostQueryService,
    private val memberQueryService: MemberQueryService,
) {

    fun create(memberId: Long, postId: Long, command: CreateCommentCommand): Comment {
        val (content, parentId) = command

        val member = memberQueryService.getMemberByIdOrThrow(memberId)
        val post = postQueryService.getPostByIdOrThrow(postId)
        val parent = parentId?.let {
            commentQueryService.getCommentByIdOrThrow(parentId)
        }

        return commentRepository.save(
            Comment(
                content = content,
                post = post,
                member = member,
                parent = parent
            )
        )
    }

    fun delete(commentId: Long) =
        commentQueryService.getCommentByIdOrThrow(commentId).let { comment ->
            commentRepository.delete(comment)
        }
}
