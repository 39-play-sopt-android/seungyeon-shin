package org.sopt.play.presentation.post

// 화면에 보여줄 게시글 하나 (서버 DTO 그대로 안 쓰고 화면용으로)
data class Post(
    val id: Long,
    val author: String,
    val date: String,
    val title: String,
    val content: String,
)

data class PostUiState(
    val posts: List<Post> = emptyList(),
    val isBottomSheetVisible: Boolean = false,
    val deleteTargetId: Long? = null, // null 아니면 삭제 모달 띄움
)
