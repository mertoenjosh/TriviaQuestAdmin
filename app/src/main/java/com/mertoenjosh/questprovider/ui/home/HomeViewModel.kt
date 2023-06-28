package com.mertoenjosh.questprovider.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mertoenjosh.questprovider.domain.repositories.AuthRepo
import com.mertoenjosh.questprovider.domain.repositories.QuestionRepo
import com.mertoenjosh.questprovider.ui.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val questionRepo: QuestionRepo,
    private val authRepo: AuthRepo
) : ViewModel() {
    private val _homeScreenState = MutableStateFlow(HomeScreenState())
    val homeScreenState get() = _homeScreenState.asStateFlow()
    val allQuestions
        get() = questionRepo.getAllQuestions().cachedIn(viewModelScope)

    fun handleHomeScreenEvents(homeScreenEvents: HomeScreenEvents) {
        when (homeScreenEvents) {
            is HomeScreenEvents.ProfileClicked -> {
                getLoggedInUserProfile()
            }

            is HomeScreenEvents.QuestionClicked -> {

            }
        }

    }

    private fun getQuestions() = viewModelScope.launch {
        try {
            val response = questionRepo.getAllQuestions().cachedIn(viewModelScope)


        } catch (e: Exception) {
            Timber.e(e, "Exception Fetching Questions occurred: %s", e.message)

            _homeScreenState.update {
                it.copy(uiState = e.localizedMessage?.let { message -> UiState.Error(message) })
            }
        }
    }

    private fun getLoggedInUserProfile() {
        viewModelScope.launch {
            _homeScreenState.update { it.copy(uiState = UiState.Loading()) }
            try {
                val response = authRepo.getUser()

                response.collectLatest { user ->
                    _homeScreenState.update { homeScreenState ->
                        homeScreenState.copy(uiState = UiState.Success(user))
                    }
                }
            } catch (e: Exception) {
                Timber.e(e, "Exception Occurred: %s", e.message)

                _homeScreenState.update {
                    it.copy(uiState = e.localizedMessage?.let { message -> UiState.Error(message) })
                }
            }
        }
    }

}