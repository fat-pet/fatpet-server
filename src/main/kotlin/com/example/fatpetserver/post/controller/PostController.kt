package com.example.fatpetserver.post.controller

import com.example.fatpetserver.common.ApiResponse
import com.example.fatpetserver.common.auth.UserDetails
import com.example.fatpetserver.post.repository.PostInfo
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/posts")
class PostController : PostApi {

    override fun getAll(userDetails: UserDetails): ApiResponse<List<PostInfo>> {
        TODO("Not yet implemented")
    }

    override fun create(userDetails: UserDetails) {
        TODO("Not yet implemented")
    }

    override fun update(id: Long) {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long) {
        TODO("Not yet implemented")
    }
}
