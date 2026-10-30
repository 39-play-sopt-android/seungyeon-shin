package org.sopt.play.presentation.home

data class HomeUiState(
    val selectedMenu: Int = 1,        // 기본 "홈"
    val selectedCategory: Int = 0,    // 기본 "전체"
    val products: List<Product> = homeProducts, // 좋아요 바뀌어야 해서 상태로 들고 있음
)
