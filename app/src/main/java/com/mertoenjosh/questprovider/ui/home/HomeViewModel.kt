package com.mertoenjosh.questprovider.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import com.mertoenjosh.questprovider.data.repositories.RepositoryImpl
import com.mertoenjosh.questprovider.domain.models.Question
import com.mertoenjosh.questprovider.navigation.Screen
import com.mertoenjosh.questprovider.util.ScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import timber.log.Timber
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: RepositoryImpl
): ViewModel() {
    private val _events = Channel<ScreenEvent>()
    val events = _events.receiveAsFlow()

    val getAllQuestion = repository.getAllQuestions()

    fun onQuestionClick(question: Question) {
        viewModelScope.launch {
            // TODO: Navigate to question details' screen
            val questionJsonElement = Json.encodeToJsonElement(question)
            Timber.d("MainViewModelTAG: %s", questionJsonElement)
            _events.send(ScreenEvent.Navigate(Screen.Details.route))
        }
    }
}