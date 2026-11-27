package org.sopt.play.presentation.post.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.sopt.play.core.designsystem.theme.Gray1
import org.sopt.play.core.designsystem.theme.Gray5
import org.sopt.play.core.designsystem.theme.Gray6
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.designsystem.theme.White
import org.sopt.play.core.designsystem.theme.m18
import org.sopt.play.core.designsystem.theme.sb14
import org.sopt.play.ui.component.PlayButton
import org.sopt.play.util.noRippleClickable

@Composable
fun PostDeleteModal(
    onDismiss: () -> Unit,
    onDeleteClick: () -> Unit,
) {
    // Dialog -> 뒤에 어두운 배경 + 바깥 누르면 닫힘 알아서 해줌
    Dialog(onDismissRequest = onDismiss) {
        PostDeleteModalContent(
            onCancelClick = onDismiss,
            onDeleteClick = onDeleteClick,
        )
    }
}

// 프리뷰에서 Dialog는 안 보여서 안쪽만 따로 뺌
@Composable
private fun PostDeleteModalContent(
    onCancelClick: () -> Unit,
    onDeleteClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .width(288.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(White)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        Text(
            text = "게시글을 삭제할까요?",
            style = m18,
            color = Gray6,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            // 취소 버튼은 회색 스타일이라 PlayButton이랑 색이 달라서 따로
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(100.dp))
                    .background(Gray1)
                    .noRippleClickable(onClick = onCancelClick)
                    .padding(16.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = "취소", style = sb14, color = Gray5)
            }
            PlayButton(
                text = "삭제",
                onClick = onDeleteClick,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Preview
@Composable
private fun PostDeleteModalPreview() {
    PlaySoptTheme {
        PostDeleteModalContent(onCancelClick = {}, onDeleteClick = {})
    }
}
