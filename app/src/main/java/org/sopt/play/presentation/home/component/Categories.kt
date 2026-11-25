package org.sopt.play.presentation.home.component

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

@Preview(showBackground = true, widthDp = 720) // 프리뷰 폭 넓혀서 가로로 긴 것도 다 보이게
@Composable
private fun CategoriesPreview() {
    PlaySoptTheme {
        Categories(
            categories = listOf("전체", "상의", "팬츠", "원피스", "아우터", "스커트", "슈즈", "가방", "이너웨어/잠옷", "폰꾸미기"),
            selectedIndex = 0,
            onCategoryClick = {},
        )
    }
}
