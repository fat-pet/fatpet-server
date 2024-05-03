package com.example.fatpetserver.post.controller

import com.example.fatpetserver.common.ApiResponse
import com.example.fatpetserver.common.auth.UserDetails
import com.example.fatpetserver.post.repository.PostInfo
import com.example.fatpetserver.post.service.PostCommandService
import com.example.fatpetserver.post.service.PostQueryService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/posts")
class PostController(
    private val postCommandService: PostCommandService,
    private val postQueryService: PostQueryService,
) : PostApi {

    override fun getAll(userDetails: UserDetails): ApiResponse<List<PostInfo>> {
        return ApiResponse.success(postQueryService.getAll())
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
