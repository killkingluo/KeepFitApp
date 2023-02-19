package com.example.keepfitapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KeepFitApp: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: KeepFitApp
    }
}