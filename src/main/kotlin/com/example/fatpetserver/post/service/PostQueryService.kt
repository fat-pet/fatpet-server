package com.example.fatpetserver.post.service

import com.example.fatpetserver.post.dto.PostDetailResponse
import com.example.fatpetserver.post.entity.Post
import com.example.fatpetserver.post.repository.CommentRepository
import com.example.fatpetserver.post.repository.PostInfo
import com.example.fatpetserver.post.repository.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PostQueryService(
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository,
) {

    fun getAll(): List<PostInfo> = postRepository.findAllByOrderByCreatedDateDesc()

    fun getPostDetail(postId: Long): PostDetailResponse {
        val post = getPostByIdOrThrow(postId)
        val comments = commentRepository.findAllByPostIdAndParentIdIsNull(postId)

        return with(post) {
            PostDetailResponse(
                id = id,
                createdDate = createdDate,
                nickname = member.nickname,
                title = title,
                content = content,
                comments = comments
            )
        }
    }

    fun getPostByIdOrThrow(postId: Long): Post =
        postRepository.findByIdOrNull(postId)
            ?: throw IllegalArgumentException("존재하지 않는 게시글입니다.")
}
