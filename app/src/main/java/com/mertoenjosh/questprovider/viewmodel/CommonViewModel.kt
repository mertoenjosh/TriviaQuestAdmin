package com.mertoenjosh.questprovider.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CommonViewModel @Inject constructor(): ViewModel() {
    private var _openDialog = MutableStateFlow(false)
    val openDialog = _openDialog.asStateFlow()

    fun openDialog() {
        _openDialog.value = true
    }

    fun closeDialog() {
        _openDialog.value = false
    }
}