package org.sopt.play.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.ui.component.Chip
import org.sopt.play.ui.component.ProductCard
import org.sopt.play.ui.component.TabMenu
import org.sopt.play.ui.component.TopBar
import org.sopt.play.ui.theme.Black
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.White
import org.sopt.play.ui.theme.sb16

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    // 선택 상태는 Screen에서 관리 -> 컴포넌트엔 selected/onClick만 내려줌
    var selectedMenu by remember { mutableIntStateOf(1) } // 기본 "홈"
    var selectedCategory by remember { mutableIntStateOf(0) } // 기본 "전체"

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
                LazyRow {
                    itemsIndexed(homeMenus) { index, menu ->
                        TabMenu(
                            text = menu,
                            selected = index == selectedMenu,
                            onClick = { selectedMenu = index },
                        )
                    }
                }
            }

            item(span = { GridItemSpan(maxLineSpan) }) {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(homeBanners) { bannerRes ->
                        Image(
                            painter = painterResource(bannerRes),
                            contentDescription = "배너",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(328.dp)
                                .height(195.dp)
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
                LazyRow(
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    itemsIndexed(homeCategories) { index, category ->
                        Chip(
                            text = category,
                            selected = index == selectedCategory,
                            onClick = { selectedCategory = index },
                        )
                    }
                }
            }

            // 상품은 일반 칸 -> 2개씩 알아서 줄바꿈
            items(homeProducts) { product ->
                ProductCard(
                    storeName = product.storeName,
                    productName = product.productName,
                    discountRate = product.discountRate,
                    price = product.price,
                    isFavorite = product.isFavorite,
                    imageRes = product.imageRes,
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
