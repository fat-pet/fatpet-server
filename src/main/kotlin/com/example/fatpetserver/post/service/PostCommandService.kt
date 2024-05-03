package com.example.fatpetserver.post.service

import com.example.fatpetserver.post.repository.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PostCommandService(private val postRepository: PostRepository)
