package com.mertoenjosh.questprovider.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertoenjosh.questprovider.data.models.request.LoginRequest
import com.mertoenjosh.questprovider.data.models.response.UserResponse
import com.mertoenjosh.questprovider.data.repos.Repository
import com.mertoenjosh.questprovider.util.ScreenEvent
import com.mertoenjosh.questprovider.util.Utils.handler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _loginLiveData = MutableLiveData<UserResponse>(null)
    val loginLiveData = _loginLiveData as LiveData<UserResponse>

    // test@mnt.dev pass1234
    fun loginUser(loginObj: LoginRequest) {
        _loginLiveData.value = null
        viewModelScope.launch(handler) {
            val loginResponse = repository.loginUser(loginObj)

            loginResponse.let {
                Timber.d("User %s", it)

                _loginLiveData.value = it
            }
        }
    }
}