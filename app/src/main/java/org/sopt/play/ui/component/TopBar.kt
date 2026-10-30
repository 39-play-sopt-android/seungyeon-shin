package org.sopt.play.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.R
import org.sopt.play.ui.theme.Black
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.White
import org.sopt.play.ui.theme.b28

// 왼쪽 로고 + 오른쪽 검색 아이콘 가로로 나란히 -> Row
// 클릭이나 선택 상태 없어서 파라미터는 modifier 하나
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(White)
            .padding(start = 16.dp, end=6.dp),
        horizontalArrangement = Arrangement.SpaceBetween, // 양 끝으로 벌리기
        verticalAlignment = Alignment.CenterVertically, // 글자랑 아이콘 높이 달라서 세로 가운데 맞춤
    ) {
        Text(text = "PLAY SOPT", style = b28, color = Black)
        // drawable 아이콘은 painterResource로 불러옴, Icon은 tint로 색 바꿀 수 있음
        Icon(
            painter = painterResource(R.drawable.ic_search),
            contentDescription = "검색",
            tint = Black,
            modifier = Modifier
                .padding(10.dp)
                .size(28.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TopBarPreview() {
    PlaySoptTheme {
        TopBar()
    }
}
