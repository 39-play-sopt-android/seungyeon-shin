package org.sopt.play.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.sopt.play.BuildConfig
import org.sopt.play.data.service.PostService
import retrofit2.Retrofit

object RetrofitClient {
    // 서버가 DTO에 없는 값 줘도 안 터지게
    private val json = Json { ignoreUnknownKeys = true }

    // Logcat에서 okhttp 검색하면 요청/응답 다 보임
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(client)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    val postService: PostService = retrofit.create(PostService::class.java)
}
