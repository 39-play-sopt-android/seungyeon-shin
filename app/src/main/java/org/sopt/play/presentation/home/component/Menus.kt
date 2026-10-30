package org.sopt.play.presentation.home.component

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.play.ui.theme.PlaySoptTheme

// TabMenu 여러 개 가로로 모은 거 (피그마 "Menus")
// 선택된 index만 받고, 누르면 index 올려보냄 -> 상태는 HomeScreen이 가짐
@Composable
fun Menus(
    menus: List<String>,
    selectedIndex: Int,
    onMenuClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(modifier = modifier) {
        itemsIndexed(menus) { index, menu ->
            TabMenu(
                text = menu,
                selected = index == selectedIndex,
                onClick = { onMenuClick(index) },
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 720) // 프리뷰 폭 넓혀서 가로로 긴 것도 다 보이게
@Composable
private fun MenusPreview() {
    PlaySoptTheme {
        Menus(
            menus = listOf("2배적립", "홈", "셀렉티드", "브랜드", "뷰티", "라이프", "랭킹"),
            selectedIndex = 1,
            onMenuClick = {},
        )
    }
}
