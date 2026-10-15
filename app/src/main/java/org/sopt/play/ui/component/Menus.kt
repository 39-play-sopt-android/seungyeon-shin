package org.sopt.play.ui.component

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

@Preview(showBackground = true)
@Composable
private fun MenusPreview() {
    PlaySoptTheme {
        Menus(
            menus = listOf("2배적립", "홈", "셀렉티드", "브랜드"),
            selectedIndex = 1,
            onMenuClick = {},
        )
    }
}
