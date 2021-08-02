package com.johnnyzhou.movieme.ui.common

sealed class UiState {
    object Error : UiState()
    object Loading : UiState()
    object Success : UiState()
}