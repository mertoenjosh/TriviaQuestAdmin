package com.mertoenjosh.questprovider.util

import androidx.compose.ui.focus.FocusDirection
import com.mertoenjosh.questprovider.util.inputValidations.FocusedTextFieldKey

sealed class ScreenEvent {
    class ShowToast(val message: String) : ScreenEvent()
    class ShowSnackBar(val message: String) : ScreenEvent()
    class UpdateKeyboard(val show: Boolean) : ScreenEvent()
    class RequestFocus(val textFieldKey: FocusedTextFieldKey) : ScreenEvent()
    object ClearFocus : ScreenEvent()
    class MoveFocus(val direction: FocusDirection = FocusDirection.Down) : ScreenEvent()
    class Navigate(val destination: String) : ScreenEvent()
    class ShowLoader(val isLoading: Boolean = false): ScreenEvent()
}