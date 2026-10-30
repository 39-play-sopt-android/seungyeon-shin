package org.sopt.play.presentation.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onMenuClick(index: Int) {
        _uiState.update { it.copy(selectedMenu = index) }
    }

    fun onCategoryClick(index: Int) {
        _uiState.update { it.copy(selectedCategory = index) }
    }

    // 누른 상품만 isFavorite 뒤집기 -> 나머지는 그대로
    fun onFavoriteClick(index: Int) {
        _uiState.update { state ->
            state.copy(
                products = state.products.mapIndexed { i, product ->
                    if (i == index) product.copy(isFavorite = !product.isFavorite) else product
                },
            )
        }
    }
}