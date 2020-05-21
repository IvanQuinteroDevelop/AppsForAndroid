package com.example.practicapp.di

import com.example.practicapp.views.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DataModule::class, ModuleApp::class])
@Singleton
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)
}