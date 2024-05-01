package com.example.fatpetserver.post.repository

import com.example.fatpetserver.post.entity.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long>
