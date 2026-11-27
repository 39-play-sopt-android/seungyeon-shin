package org.sopt.play.presentation.post.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.theme.Gray3
import org.sopt.play.core.designsystem.theme.White
import org.sopt.play.ui.component.PlayButton
import org.sopt.play.ui.component.PlayTextField

private const val TITLE_MAX_LENGTH = 20
private const val CONTENT_MAX_LENGTH = 1000

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostBottomSheet(
    onDismiss: () -> Unit,
    onCompleteClick: (title: String, content: String) -> Unit,
    modifier: Modifier = Modifier,
    initialTitle: String = "", // 심화 수정할 때 원래 값 넣으려고 미리 뚫어둠
    initialContent: String = "",
) {
    var title by remember { mutableStateOf(initialTitle) }
    var content by remember { mutableStateOf(initialContent) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        modifier = modifier,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true), // 반만 열리는 상태 없이 한 번에 쭉
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        containerColor = White,
        dragHandle = {
            // 피그마 손잡이: 36x4 회색 + 위12 아래20
            Box(
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 20.dp)
                    .size(width = 36.dp, height = 4.dp)
                    .background(Gray3, RoundedCornerShape(100.dp)),
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                PlayTextField(
                    label = "제목",
                    value = title,
                    // 글자수 넘으면 아예 안 바뀌게 -> 텍스트필드 차원에서 제한
                    onValueChange = { if (it.length <= TITLE_MAX_LENGTH) title = it },
                    placeholder = "제목을 입력하세요.",
                )
                PlayTextField(
                    label = "본문",
                    value = content,
                    onValueChange = { if (it.length <= CONTENT_MAX_LENGTH) content = it },
                    placeholder = "본문을 입력하세요.",
                )
            }
            PlayButton(
                text = "완료",
                onClick = { onCompleteClick(title, content) },
                enabled = title.isNotBlank() && content.isNotBlank(), // 둘 다 써야 활성화
            )
        }
    }
}
