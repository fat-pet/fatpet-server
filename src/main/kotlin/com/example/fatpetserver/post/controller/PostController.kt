package com.example.fatpetserver.post.controller

import com.example.fatpetserver.common.auth.UserDetails
import com.example.fatpetserver.common.dto.ApiResponse
import com.example.fatpetserver.post.dto.CreateCommentCommand
import com.example.fatpetserver.post.dto.CreatePostCommand
import com.example.fatpetserver.post.dto.PostDetailResponse
import com.example.fatpetserver.post.dto.UpdatePostCommand
import com.example.fatpetserver.post.repository.PostInfo
import com.example.fatpetserver.post.service.CommentCommandService
import com.example.fatpetserver.post.service.PostCommandService
import com.example.fatpetserver.post.service.PostQueryService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/posts")
class PostController(
    private val postCommandService: PostCommandService,
    private val postQueryService: PostQueryService,
    private val commentCommandService: CommentCommandService,
) : PostApi {

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    override fun getAll(): ApiResponse<List<PostInfo>> {
        return ApiResponse.success(postQueryService.getAll())
    }

    @GetMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    override fun getPostDetail(@PathVariable postId: Long): ApiResponse<PostDetailResponse> {
        return ApiResponse.success(postQueryService.getPostDetail(postId))
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    override fun create(
        @AuthenticationPrincipal userDetails: UserDetails,
        @Valid @RequestBody command: CreatePostCommand,
    ) {
        postCommandService.create(userDetails.id, command)
    }

    @PutMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    override fun update(
        @PathVariable postId: Long,
        @Valid @RequestBody command: UpdatePostCommand,
    ) {
        postCommandService.update(postId, command)
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    override fun delete(@PathVariable postId: Long) {
        postCommandService.delete(postId)
    }

    @PostMapping("/{postId}/comments")
    @ResponseStatus(value = HttpStatus.CREATED)
    override fun createComment(
        @AuthenticationPrincipal userDetails: UserDetails,
        @PathVariable postId: Long,
        @Valid @RequestBody command: CreateCommentCommand,
    ) {
        commentCommandService.create(userDetails.id, postId, command)
    }

    @DeleteMapping("/comments/{commentId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    override fun deleteComment(@PathVariable commentId: Long) {
        commentCommandService.delete(commentId)
    }
}
