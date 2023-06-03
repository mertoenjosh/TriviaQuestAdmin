package com.mertoenjosh.questprovider.util

import android.content.Context
import android.widget.Toast

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun String.capitalize() = if (this.isNotBlank()) {
    this.lowercase().replaceFirst(this[0], this[0].uppercaseChar())
} else ""
