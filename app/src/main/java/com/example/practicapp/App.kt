package com.example.practicapp

import android.app.Application
import com.example.practicapp.di.ApplicationComponent


class App : Application() {
    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        applicationComponent
        super.onCreate()
    }
    fun getComponent(): ApplicationComponent {
        return applicationComponent
    }
}