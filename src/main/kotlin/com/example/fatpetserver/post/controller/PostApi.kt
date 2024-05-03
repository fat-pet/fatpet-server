package com.example.fatpetserver.post.controller

import com.example.fatpetserver.common.ApiResponse
import com.example.fatpetserver.common.auth.UserDetails
import com.example.fatpetserver.post.dto.CreatePostCommand
import com.example.fatpetserver.post.repository.PostInfo
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "Post API")
interface PostApi {

    @Operation(summary = "전체 게시글 목록 조회")
    fun getAll(userDetails: UserDetails): ApiResponse<List<PostInfo>>

    @Operation(summary = "게시글 생성")
    fun create(userDetails: UserDetails, command: CreatePostCommand)

    @Operation(summary = "게시글 수정")
    fun update(id: Long)

    @Operation(summary = "게시글 삭제")
    fun delete(id: Long)
}

