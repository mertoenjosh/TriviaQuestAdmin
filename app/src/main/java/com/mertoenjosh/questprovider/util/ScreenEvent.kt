package com.mertoenjosh.questprovider.util

sealed class ScreenEvent {
    class Navigate(val destination: String) : ScreenEvent()
}