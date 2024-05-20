package com.example.fatpetserver.post.controller

import com.example.fatpetserver.common.auth.UserDetails
import com.example.fatpetserver.common.dto.ApiResponse
import com.example.fatpetserver.post.dto.CreateCommentCommand
import com.example.fatpetserver.post.dto.CreatePostCommand
import com.example.fatpetserver.post.dto.PostDetailResponse
import com.example.fatpetserver.post.dto.UpdatePostCommand
import com.example.fatpetserver.post.repository.PostInfo
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "Post API")
interface PostApi {

    @Operation(summary = "전체 게시글 목록 조회")
    fun getAll(): ApiResponse<List<PostInfo>>

    @Operation(summary = "게시글 상세 조회")
    fun getPostDetail(postId: Long): ApiResponse<PostDetailResponse>

    @Operation(summary = "게시글 생성")
    fun create(userDetails: UserDetails, command: CreatePostCommand)

    @Operation(summary = "게시글 수정")
    fun update(postId: Long, command: UpdatePostCommand)

    @Operation(summary = "게시글 삭제")
    fun delete(postId: Long)

    @Operation(summary = "댓글 생성")
    fun createComment(userDetails: UserDetails, postId: Long, command: CreateCommentCommand)

    @Operation(summary = "댓글 삭제")
    fun deleteComment(commentId: Long)
}

