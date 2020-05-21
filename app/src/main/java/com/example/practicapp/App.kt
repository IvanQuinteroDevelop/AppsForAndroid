package com.example.practicapp

import android.app.Application
import com.example.practicapp.di.ApplicationComponent
import com.example.practicapp.di.DaggerApplicationComponent
import com.example.practicapp.di.DataModule
import com.example.practicapp.di.ModuleApp

class App : Application() {
    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        applicationComponent = DaggerApplicationComponent.builder()
            .dataModule(DataModule()).moduleApp(ModuleApp(this))
            .build()

        super.onCreate()
    }

    fun getComponent(): ApplicationComponent {
        return applicationComponent
    }

}