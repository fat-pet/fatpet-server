package com.example.fatpetserver.post.service

import com.example.fatpetserver.member.service.MemberQueryService
import com.example.fatpetserver.post.dto.CreatePostCommand
import com.example.fatpetserver.post.dto.UpdatePostCommand
import com.example.fatpetserver.post.entity.Post
import com.example.fatpetserver.post.repository.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PostCommandService(
    private val postRepository: PostRepository,
    private val postQueryService: PostQueryService,
    private val memberQueryService: MemberQueryService,
) {

    fun create(memberId: Long, command: CreatePostCommand): Post {
        val (title, content) = command

        val member = memberQueryService.getMemberByIdOrThrow(memberId)

        return postRepository.save(
            Post(
                title = title,
                content = content,
                member = member,
            )
        )
    }

    fun update(postId: Long, command: UpdatePostCommand) {
        val (newTitle, newContent) = command

        val updatedPost = postQueryService.getPostByIdOrThrow(postId).apply {
            title = newTitle
            content = newContent
        }

        postRepository.save(updatedPost)
    }

    fun delete(postId: Long) =
        postQueryService.getPostByIdOrThrow(postId).let { post ->
            postRepository.delete(post)
        }
}
