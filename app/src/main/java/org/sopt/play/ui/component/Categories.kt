package org.sopt.play.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.ui.theme.PlaySoptTheme

// Chip 여러 개 가로로 모은 거 (피그마 "Categories") -> Menus랑 구조 똑같음
@Composable
fun Categories(
    categories: List<String>,
    selectedIndex: Int,
    onCategoryClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp), // 처음/끝 여백 (스크롤하면 같이 움직임)
        horizontalArrangement = Arrangement.spacedBy(6.dp), // 칩 사이 간격
    ) {
        itemsIndexed(categories) { index, category ->
            Chip(
                text = category,
                selected = index == selectedIndex,
                onClick = { onCategoryClick(index) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoriesPreview() {
    PlaySoptTheme {
        Categories(
            categories = listOf("전체", "상의", "팬츠", "원피스"),
            selectedIndex = 0,
            onCategoryClick = {},
        )
    }
}
