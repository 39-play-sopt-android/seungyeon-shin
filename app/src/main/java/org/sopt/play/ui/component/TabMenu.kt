package org.sopt.play.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color // android.graphics.Color 아님!! compose 쪽 Color 써야 Transparent 있음
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.ui.theme.Black
import org.sopt.play.ui.theme.Gray3
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.m18
import org.sopt.play.util.noRippleClickable

/*
- 세로로 쌓임(Column), 가운데 정렬, 요소 사이 4dp, 안쪽 여백 좌우 16dp / 위아래 8dp
- 위: 4dp 동그라미 점 / 아래: 글자 m18
- 선택됨: 점 Black, 글자 Black / 선택 안 됨: 점 투명, 글자 Gray3
- 점을 안 그리면 높이가 달라지니까 항상 그리고 색만 투명으로 바꿈
 */
@Composable
fun TabMenu(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier, // 밖에서 받은 modifier는 맨 바깥 하나에만 씀 (안쪽엔 새로 Modifier)
) {
    // 선택 상태는 내가 기억 안 하고 selected로 받기만 함 -> 홈 화면이 누가 선택됐는지 관리
    val dotColor = if (selected) Black else Color.Transparent
    val textColor = if (selected) Black else Gray3

    // 점이랑 글자 세로로 쌓는 거라 Column 하나면 충분 (Box로 한 번 더 감쌀 필요 없음)
    Column(
        modifier = modifier
            .noRippleClickable { onClick() } // 클릭 먼저 걸고 padding -> 여백까지 눌림
            .padding(horizontal = 16.dp, vertical = 8.dp),
        // 정렬/간격은 modifier가 아니라 Column 파라미터로 줌
        horizontalAlignment = Alignment.CenterHorizontally, // 가로 가운데
        verticalArrangement = Arrangement.spacedBy(4.dp), // 자식들 사이 간격
    ) {
        Box(modifier = Modifier.size(4.dp).background(dotColor, CircleShape))
        Text(text = text, style = m18, color = textColor)
    }
}

@Preview(showBackground = true)
@Composable
private fun TabMenuPreview() {
    PlaySoptTheme {
        Row {
            TabMenu(text = "메뉴", selected = true, onClick = {})
            TabMenu(text = "메뉴", selected = false, onClick = {})
        }
    }
}
