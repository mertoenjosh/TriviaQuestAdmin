package com.mertoenjosh.questprovider.ui.home

import com.mertoenjosh.questprovider.ui.util.UiState

data class HomeScreenState <T> (
    val uiState: UiState<T>? = null
)

sealed class HomeScreenEvents {
    object QuestionClicked: HomeScreenEvents()
    object ProfileClicked: HomeScreenEvents()
}