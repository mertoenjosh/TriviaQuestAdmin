package com.mertoenjosh.questprovider.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mertoenjosh.questprovider.data.database.models.QuestionEntity
import com.mertoenjosh.questprovider.domain.models.User
import com.mertoenjosh.questprovider.domain.repositories.AuthRepo
import com.mertoenjosh.questprovider.domain.repositories.QuestionRepo
import com.mertoenjosh.questprovider.ui.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
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
    private val _homeScreenState = MutableStateFlow<HomeScreenState<User>>(HomeScreenState())
    val homeScreenState get() = _homeScreenState.asStateFlow()

    fun handleHomeScreenEvents(homeScreenEvents: HomeScreenEvents) {
        when (homeScreenEvents) {
            is HomeScreenEvents.ProfileClicked -> {
                getLoggedInUserProfile()
            }

            is HomeScreenEvents.QuestionClicked -> {

            }
        }

    }

    fun getAllQuestion(): Flow<PagingData<QuestionEntity>> =
        questionRepo.getAllQuestions().cachedIn(viewModelScope)

    fun getQuestions() = viewModelScope.launch {
        _homeScreenState.update { it.copy(uiState = UiState.Loading()) }
        try {
            val response = questionRepo.getAllQuestions().cachedIn(viewModelScope)

//            response.collectLatest { pagingData ->
//                _homeScreenState.update { homeScreenState ->
//                    homeScreenState.copy(uiState = UiState.Success(pagingData))
//                }
//            }
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