package com.mertoenjosh.questprovider.util

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}