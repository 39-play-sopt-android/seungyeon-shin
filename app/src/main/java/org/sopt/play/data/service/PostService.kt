package org.sopt.play.data.service

import org.sopt.play.data.dto.BaseResponse
import org.sopt.play.data.dto.PostListResponse
import org.sopt.play.data.dto.PostRequest
import org.sopt.play.data.dto.PostResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PostService {
    // 목록 조회 -> Postman Params 탭 = @Query
    @GET("api/v1/posts")
    suspend fun getPosts(
        @Query("size") size: Int = 30,
    ): BaseResponse<PostListResponse>

    // 생성 -> Headers 탭 = @Header, Body 탭 = @Body
    @POST("api/v1/posts")
    suspend fun postPost(
        @Header("Authorization") authorizationCode: String,
        @Body request: PostRequest,
    ): BaseResponse<PostResponse>

    // 삭제 -> URL 속 id = @Path
    @DELETE("api/v1/posts/{postId}")
    suspend fun deletePost(
        @Header("Authorization") authorizationCode: String,
        @Path("postId") postId: Long,
    ): BaseResponse<Unit>
}
