package com.mertoenjosh.questprovider.ui.util

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InputWrapper(
    val value: String = "",
    val errorId: Int? = null
) : Parcelable