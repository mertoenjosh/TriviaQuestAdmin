package com.mertoenjosh.triviaquestadmin.application

import android.app.Application
import com.mertoenjosh.triviaquestadmin.BuildConfig
import timber.log.Timber

class ApplicationController: Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}