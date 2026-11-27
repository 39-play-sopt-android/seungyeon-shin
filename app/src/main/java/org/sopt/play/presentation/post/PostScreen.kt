package org.sopt.play.presentation.post

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.designsystem.theme.White
import org.sopt.play.presentation.post.component.PostBottomSheet
import org.sopt.play.presentation.post.component.PostCard
import org.sopt.play.presentation.post.component.PostDeleteModal
import org.sopt.play.ui.component.PlayFloatingButton
import org.sopt.play.ui.component.PlaySearchTextField

@Composable
fun PostRoute(
    author: String, viewModel: PostViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    // ViewModel이 보낸 메시지 받으면 토스트
    LaunchedEffect(Unit) {
        viewModel.toastMessage.collect { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    PostScreen(
        uiState = uiState,
        onFloatingButtonClick = viewModel::onFloatingButtonClick,
        onPostLongClick = viewModel::onPostLongClick,
    )

    if (uiState.isBottomSheetVisible) {
        PostBottomSheet(
            onDismiss = viewModel::onBottomSheetDismiss,
            onCompleteClick = { title, content -> viewModel.createPost(title, content, author) },
        )
    }

    uiState.deleteTargetId?.let {
        PostDeleteModal(
            onDismiss = viewModel::onDeleteModalDismiss,
            onDeleteClick = viewModel::deletePost,
        )
    }
}

@Composable
fun PostScreen(
    uiState: PostUiState,
    onFloatingButtonClick: () -> Unit,
    onPostLongClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    // 필수에선 검색창 모양만 -> 글자만 써지게 여기서 들고 있음 (심화 때 ViewModel로)
    var keyword by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(White),
    ) {
        Column {
            PlaySearchTextField(
                value = keyword,
                onValueChange = { keyword = it },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            )
            LazyColumn(
                // 아래 100 -> 마지막 카드가 + 버튼에 안 가려지게
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 100.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(items = uiState.posts, key = { it.id }) { post ->
                    PostCard(
                        author = post.author,
                        date = post.date,
                        title = post.title,
                        content = post.content,
                        onClick = {}, // 심화(수정)에서 채움
                        onLongClick = { onPostLongClick(post.id) },
                    )
                }
            }
        }
        PlayFloatingButton(
            onClick = onFloatingButtonClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 20.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PostScreenPreview() {
    PlaySoptTheme {
        PostScreen(
            uiState = PostUiState(
                posts = listOf(
                    Post(1, "작성자", "YYYY.MM.DD", "제목", "본문입니다."),
                    Post(2, "작성자", "YYYY.MM.DD", "제목", "본문입니다. 본문입니다. 본문입니다. 본문입니다. 본문입니다. 본문입니다."),
                    Post(3, "작성자", "YYYY.MM.DD", "제목", "본문입니다."),
                ),
            ),
            onFloatingButtonClick = {},
            onPostLongClick = {},
        )
    }
}
