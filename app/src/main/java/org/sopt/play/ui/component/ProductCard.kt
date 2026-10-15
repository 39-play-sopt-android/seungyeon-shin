package org.sopt.play.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.R
import org.sopt.play.ui.theme.Black
import org.sopt.play.ui.theme.Gray1
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.Red
import org.sopt.play.ui.theme.White
import org.sopt.play.ui.theme.m14

/*
- 위: 이미지 영역(160:200 비율), 오른쪽 아래에 하트
- 아래: 스토어명 / 상품명(각각 최대 한 줄, 넘치면 …) / 할인율 + 가격
- 좋아요는 과제 안내대로 상태관리 없이 isFavorite 값으로 고정
 */
@Composable
fun ProductCard(
    storeName: String,
    productName: String,
    discountRate: String,
    price: String,
    isFavorite: Boolean,
    modifier: Modifier = Modifier,
    @DrawableRes imageRes: Int? = null, // 상품 이미지 (없으면 회색 박스)
) {
    Column(modifier = modifier) {
        // 이미지 위에 하트 겹쳐야 해서 Box (Box 안 자식들은 위로 쌓임)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(160f / 200f), // 폭이 바뀌어도 160:200 비율 유지
        ) {
            if (imageRes != null) {
                Image(
                    painter = painterResource(imageRes),
                    contentDescription = productName,
                    contentScale = ContentScale.Crop, // 비율 안 맞아도 꽉 채우고 넘치는 부분 자름
                    modifier = Modifier.fillMaxSize(),
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Gray1),
                )
            }
            // 하트: Icon + tint로 색 직접 지정 -> 선택이면 빨강, 아니면 흰색
            // 40dp 박스(터치 영역) 가운데에 24dp 아이콘
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd) // Box 안에서 이것만 오른쪽 아래로
                    .size(40.dp),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    painter = painterResource(
                        if (isFavorite) R.drawable.ic_favorite_fill else R.drawable.ic_favorite,
                    ),
                    contentDescription = if (isFavorite) "좋아요 선택됨" else "좋아요 선택 안 됨",
                    modifier = Modifier.size(24.dp),
                    tint = if (isFavorite) Red else White,
                )
            }
        }

        Column(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 12.dp, bottom = 20.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(
                text = storeName,
                style = m14,
                color = Black,
                maxLines = 1, // 최대 한 줄
                overflow = TextOverflow.Ellipsis, // 넘치면 … 처리
            )
            // TODO: 피그마는 Regular(r14)인데 Regular 폰트가 없어서 일단 m14
            Text(
                text = productName,
                style = m14,
                color = Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(text = discountRate, style = m14, color = Red)
                Text(text = price, style = m14, color = Black)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductCardPreview() {
    PlaySoptTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            ProductCard(
                storeName = "스토어명",
                productName = "상품명",
                discountRate = "49%",
                price = "29,370",
                isFavorite = false,
                modifier = Modifier.width(160.dp),
                imageRes = R.drawable.img_product_1,
            )
            ProductCard(
                storeName = "스토어명이 아주 길어서 한 줄을 넘어가는 경우",
                productName = "상품명이 아주 길어서 한 줄을 넘어가는 경우",
                discountRate = "49%",
                price = "29,370",
                isFavorite = true,
                modifier = Modifier.width(160.dp),
            )
        }
    }
}
