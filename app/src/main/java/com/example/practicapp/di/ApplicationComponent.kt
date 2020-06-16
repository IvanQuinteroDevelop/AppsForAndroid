package com.example.practicapp.di


import com.example.practicapp.views.AuthActivity
import com.example.practicapp.views.LoginFragment
import com.example.practicapp.views.MainActivity
import com.example.practicapp.views.RegisterFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DataModule::class, ModuleApp::class])
@Singleton
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(authActivity: AuthActivity)
    fun inject(loginFragment: LoginFragment)
    fun inject(registerFragment: RegisterFragment)
}