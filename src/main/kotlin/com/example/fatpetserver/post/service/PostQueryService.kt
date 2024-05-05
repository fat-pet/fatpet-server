package com.example.fatpetserver.post.service

import com.example.fatpetserver.post.entity.Post
import com.example.fatpetserver.post.repository.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PostQueryService(private val postRepository: PostRepository) {

    fun getAll() = postRepository.findAllByOrderByCreatedDateDesc()

    fun getPostByIdOrThrow(id: Long): Post =
        postRepository.findByIdOrNull(id)
            ?: throw IllegalArgumentException("존재하지 않는 게시글입니다.")
}
