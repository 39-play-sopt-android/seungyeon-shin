package org.sopt.play.presentation.post.component

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.theme.Gray1
import org.sopt.play.core.designsystem.theme.Gray3
import org.sopt.play.core.designsystem.theme.Gray5
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.designsystem.theme.m12
import org.sopt.play.core.designsystem.theme.m14
import org.sopt.play.core.designsystem.theme.m16

@Composable
fun PostCard(
    author: String,
    date: String,
    title: String,
    content: String,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Gray1)
            // 그냥 누르기(심화 수정) + 꾹 누르기(필수 삭제) 둘 다 받으려고 combinedClickable
            .combinedClickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick,
                onLongClick = onLongClick,
            )
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = author, style = m12, color = Gray3)
            // 작성자 | 날짜 사이 세로선
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(12.dp)
                    .background(Gray3),
            )
            Text(text = date, style = m12, color = Gray3)
        }
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = title, style = m16, color = Gray5)
            Text(text = content, style = m14, color = Gray5)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PostCardPreview() {
    PlaySoptTheme {
        PostCard(
            author = "작성자",
            date = "YYYY.MM.DD",
            title = "제목",
            content = "본문입니다.",
            onClick = {},
            onLongClick = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}
