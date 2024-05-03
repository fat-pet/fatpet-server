package com.example.fatpetserver.post.service

import com.example.fatpetserver.member.service.MemberQueryService
import com.example.fatpetserver.post.dto.CreatePostCommand
import com.example.fatpetserver.post.entity.Post
import com.example.fatpetserver.post.repository.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PostCommandService(
    private val postRepository: PostRepository,
    private val memberQueryService: MemberQueryService,
) {

    fun create(id: Long, command: CreatePostCommand): Post {
        val (title, content) = command

        val member = memberQueryService.getMemberByIdOrThrow(id)

        return postRepository.save(
            Post(
                title = title,
                content = content,
                member = member,
            )
        )
    }
}
