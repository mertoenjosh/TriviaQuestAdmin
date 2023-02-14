package com.mertoenjosh.questprovider.util

import androidx.annotation.StringRes
import androidx.compose.ui.focus.FocusDirection
import com.mertoenjosh.questprovider.util.inputValidations.FocusedTextFieldKey

sealed class ScreenEvent {
    class ShowToast(@StringRes val message: Int): ScreenEvent()
    class UpdateKeyboard(val show: Boolean) : ScreenEvent()
    class RequestFocus(val textFieldKey: FocusedTextFieldKey) : ScreenEvent()
    object ClearFocus : ScreenEvent()
    class MoveFocus(val direction: FocusDirection = FocusDirection.Down) : ScreenEvent()
    class Navigate(val destination: String): ScreenEvent()
}
