package org.sopt.play.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// 생성 요청 body
@Serializable
data class PostRequest(
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String,
    @SerialName("author")
    val author: String,
)

// 게시글 하나
@Serializable
data class PostResponse(
    @SerialName("id")
    val id: Long,
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String,
    @SerialName("author")
    val author: String,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("updatedAt")
    val updatedAt: String,
)

// 목록 조회 data
@Serializable
data class PostListResponse(
    @SerialName("posts")
    val posts: List<PostResponse>,
    @SerialName("nextCursor")
    val nextCursor: Long? = null, // 다음 페이지 없으면 null
    @SerialName("hasNext")
    val hasNext: Boolean,
)
