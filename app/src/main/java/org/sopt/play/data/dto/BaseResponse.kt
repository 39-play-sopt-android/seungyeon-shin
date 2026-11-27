package org.sopt.play.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// 서버 응답이 전부 isSuccess/code/message/data 모양 -> 공통으로 하나 만들고 data만 바꿔 끼움
@Serializable
data class BaseResponse<T>(
    @SerialName("isSuccess")
    val isSuccess: Boolean,
    @SerialName("code")
    val code: String,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: T? = null, // 삭제는 data가 null로 옴 -> ? 필요
)
