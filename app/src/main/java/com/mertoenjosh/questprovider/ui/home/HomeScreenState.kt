package com.mertoenjosh.questprovider.ui.home

import androidx.paging.PagingData
import com.mertoenjosh.questprovider.data.database.models.QuestionEntity
import com.mertoenjosh.questprovider.ui.util.UiState

data class HomeScreenState (
    val questions: PagingData<QuestionEntity>? = null,
    val uiState: UiState<Any>? = null
)

sealed class HomeScreenEvents {
    object QuestionClicked: HomeScreenEvents()
    object ProfileClicked: HomeScreenEvents()
}