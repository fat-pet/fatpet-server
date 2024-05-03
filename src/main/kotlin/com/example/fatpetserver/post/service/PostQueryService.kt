package com.example.fatpetserver.post.service

import com.example.fatpetserver.post.repository.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PostQueryService(private val postRepository: PostRepository) {

    fun getAll() = postRepository.findAllByOrderByCreatedDateDesc()
}
