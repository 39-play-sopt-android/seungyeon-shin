package org.sopt.play.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.play.R
import org.sopt.play.ui.component.Categories
import org.sopt.play.ui.component.Menus
import org.sopt.play.ui.component.ProductCard
import org.sopt.play.ui.component.TopBar
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
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White),
    ) {
        // 탑바는 스크롤 밖 -> 위에 고정
        TopBar()

        // 화면 전체를 그리드 하나로 -> 위쪽 메뉴/배너/칩은 한 줄 통째로(span) 차지
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Menus(
                    menus = homeMenus,
                    selectedIndex = uiState.selectedMenu,
                    onMenuClick = onMenuClick, // it = 누른 index
                )
            }

            // 배너 한 장 가운데 -> Box를 꽉 채우고 안에서 Center 정렬
            item(span = { GridItemSpan(maxLineSpan) }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        painter = painterResource(R.drawable.img_banner_1),
                        contentDescription = "배너",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(width = 328.dp, height = 195.dp) // 피그마 크기 그대로
                            .clip(RoundedCornerShape(8.dp)),
                    )
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
