package org.sopt.play.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.play.presentation.home.component.Categories
import org.sopt.play.presentation.home.component.Menus
import org.sopt.play.presentation.home.component.ProductCard
import org.sopt.play.presentation.home.component.TopBar
import org.sopt.play.ui.theme.Black
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.White
import org.sopt.play.ui.theme.sb16

@Composable
fun HomeRoute(viewModel: HomeViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        uiState = uiState,
        onMenuClick = viewModel::onMenuClick,
        onCategoryClick = viewModel::onCategoryClick,
        onFavoriteClick = viewModel::onFavoriteClick,
    )
}
@Composable
fun HomeScreen(
    uiState: HomeUiState = HomeUiState(),
    onMenuClick: (Int) -> Unit = {},
    onCategoryClick: (Int) -> Unit = {},
    onFavoriteClick: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    // Column 대신 Scaffold -> topBar 자리에 TopBar 꽂으면 알아서 위에 고정됨 (2주차 코리 반영)
    // 나중에 bottomBar도 같은 방식으로 붙이면 됨
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TopBar() },
        containerColor = White,
        contentWindowInsets = WindowInsets(0), // 상태바 여백은 MainActivity Scaffold가 이미 줌 -> 두 번 안 들어가게 0
    ) { innerPadding ->
        // 화면 전체를 그리드 하나로 -> 위쪽 메뉴/배너/칩은 한 줄 통째로(span) 차지
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(innerPadding), // TopBar 높이만큼 내려서 안 겹치게
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Menus(
                    menus = homeMenus,
                    selectedIndex = uiState.selectedMenu,
                    onMenuClick = onMenuClick, // it = 누른 index
                )
            }

            // 배너: Box는 고정이라 스크롤 안 됨 -> 좌우로 넘기려고 LazyRow (2주차 코리 반영)
            item(span = { GridItemSpan(maxLineSpan) }) {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp), // 첫/끝 배너 여백 (스크롤하면 같이 움직임)
                    horizontalArrangement = Arrangement.spacedBy(8.dp), // 배너 사이 간격
                ) {
                    items(homeBanners) { bannerRes ->
                        Image(
                            painter = painterResource(bannerRes),
                            contentDescription = "배너",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(width = 328.dp, height = 195.dp) // 피그마 크기 그대로
                                .clip(RoundedCornerShape(8.dp)),
                        )
                    }
                }
            }

            item(span = { GridItemSpan(maxLineSpan) }) {
                Text(
                    text = "김솝트님을 위한 추천 아이템",
                    style = sb16,
                    color = Black,
                    modifier = Modifier.padding(start = 16.dp, top = 12.dp, bottom = 12.dp),
                )
            }

            item(span = { GridItemSpan(maxLineSpan) }) {
                Categories(
                    categories = homeCategories,
                    selectedIndex = uiState.selectedCategory,
                    onCategoryClick = onCategoryClick,
                    modifier = Modifier.padding(bottom = 20.dp),
                )
            }


            itemsIndexed(uiState.products) { index, product ->
                ProductCard(
                    storeName = product.storeName,
                    productName = product.productName,
                    discountRate = product.discountRate,
                    price = product.price,
                    isFavorite = product.isFavorite,
                    imageRes = product.imageRes,
                    onFavoriteClick = { onFavoriteClick(index) },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    PlaySoptTheme {
        HomeScreen()
    }
}
