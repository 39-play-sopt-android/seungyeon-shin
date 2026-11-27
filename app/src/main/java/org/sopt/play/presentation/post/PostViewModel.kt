package org.sopt.play.presentation.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.play.BuildConfig
import org.sopt.play.data.RetrofitClient
import org.sopt.play.data.dto.PostRequest
import org.sopt.play.data.dto.PostResponse
import retrofit2.HttpException

private const val AUTHOR = "승연"

class PostViewModel : ViewModel() {
    private val postService = RetrofitClient.postService

    private val _uiState = MutableStateFlow(PostUiState())
    val uiState: StateFlow<PostUiState> = _uiState.asStateFlow()

    // 토스트처럼 한 번만 보여줄 거 -> StateFlow 말고 SharedFlow
    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage: SharedFlow<String> = _toastMessage.asSharedFlow()

    init {
        getPosts()
    }

    fun getPosts() {
        viewModelScope.launch {
            runCatching { postService.getPosts() }
                .onSuccess { response ->
                    val posts = response.data?.posts.orEmpty().map { it.toPost() }
                    _uiState.update { it.copy(posts = posts) }
                }
                .onFailure { _toastMessage.emit("목록을 불러오지 못했어요") }
        }
    }

    fun onFloatingButtonClick() {
        _uiState.update { it.copy(isBottomSheetVisible = true) }
    }

    fun onBottomSheetDismiss() {
        _uiState.update { it.copy(isBottomSheetVisible = false) }
    }

    fun createPost(title: String, content: String) {
        viewModelScope.launch {
            runCatching {
                postService.postPost(
                    authorizationCode = getAuthorizationCode(),
                    request = PostRequest(title = title, content = content, author = AUTHOR),
                )
            }.onSuccess {
                _uiState.update { it.copy(isBottomSheetVisible = false) }
                getPosts() // 성공하면 목록 새로고침 -> 방금 쓴 글 맨 위에 보임
            }.onFailure {
                _toastMessage.emit("게시글을 올리지 못했어요")
            }
        }
    }

    fun onPostLongClick(postId: Long) {
        _uiState.update { it.copy(deleteTargetId = postId) }
    }

    fun onDeleteModalDismiss() {
        _uiState.update { it.copy(deleteTargetId = null) }
    }

    fun deletePost() {
        val postId = _uiState.value.deleteTargetId ?: return
        viewModelScope.launch {
            runCatching {
                postService.deletePost(
                    authorizationCode = getAuthorizationCode(),
                    postId = postId,
                )
            }.onSuccess {
                _uiState.update { it.copy(deleteTargetId = null) }
                getPosts() // 성공하면 새로고침 -> 지운 카드 사라짐
            }.onFailure { error ->
                _uiState.update { it.copy(deleteTargetId = null) }
                // 403 = 남이 쓴 글
                val message = if (error is HttpException && error.code() == 403) {
                    "내가 쓴 글만 삭제할 수 있어요"
                } else {
                    "삭제하지 못했어요"
                }
                _toastMessage.emit(message)
            }
        }
    }

    // 스웨거에서 받은 코드 -> local.properties에서 꺼내 씀
    private fun getAuthorizationCode(): String = BuildConfig.AUTH_CODE

    // "2026-10-01T12:10:00" -> "2026.10.01"
    private fun PostResponse.toPost() = Post(
        id = id,
        author = author,
        date = createdAt.take(10).replace("-", "."),
        title = title,
        content = content,
    )
}
