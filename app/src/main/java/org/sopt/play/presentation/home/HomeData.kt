package org.sopt.play.presentation.home

import androidx.annotation.DrawableRes
import org.sopt.play.R

// 상품 하나에 필요한 정보 묶음 -> data class (2차 세미나 "data class" 부분)
data class Product(
    val storeName: String,
    val productName: String,
    val discountRate: String,
    val price: String,
    val isFavorite: Boolean,
    @DrawableRes val imageRes: Int? = null,
)

// 피그마 홈 화면에 있는 텍스트 그대로 가져온 더미 데이터
val homeMenus = listOf("2배적립", "홈", "셀렉티드", "브랜드", "뷰티", "라이프", "랭킹")

// 배너는 이미지 1장 돌려씀 (가로 스크롤 보이게 3개)
val homeBanners = listOf(R.drawable.img_banner_1, R.drawable.img_banner_1, R.drawable.img_banner_1)

val homeCategories = listOf("전체", "상의", "팬츠", "원피스", "아우터", "스커트", "슈즈", "가방", "이너웨어/잠옷", "폰꾸미기")

// 이미지는 drawable에 넣은 거 R.drawable.이름 으로 연결 (파일명은 소문자/숫자/_ 만 됨)
val homeProducts = listOf(
    Product(
        storeName = "트랜디어패럴",
        productName = "[카프리1위🏆/5부,7부,9부기장/MADE] 클립 스판 슬릿 바이커 카프리 데님 팬츠",
        discountRate = "36%",
        price = "28,800",
        imageRes = R.drawable.img_product_1,
        isFavorite = false,
    ),
    Product(
        storeName = "슬로우앤드",
        productName = "[🍁가을/3기장선택!] made. 모먼트 투커버 올데이슬랙스 (+숏/베이직/롱ver) - 4 size",
        discountRate = "50%",
        price = "26,820",
        imageRes = R.drawable.img_product_2,
        isFavorite = true,
    ),
    Product(
        storeName = "어텀",
        productName = "보울7부티셔츠",
        discountRate = "10%",
        price = "17,100",
        imageRes = R.drawable.img_product_3,
        isFavorite = false,
    ),
    Product(
        storeName = "슬로우앤드",
        productName = "[오픈할인중] made. 도브 클래식 탄탄핏 무지셔츠 - 4 color",
        discountRate = "40%",
        price = "35,200",
        imageRes = R.drawable.img_product_4,
        isFavorite = false,
    ),
)
