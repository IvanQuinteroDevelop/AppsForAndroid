package com.example.practicapp.di

import com.example.practicapp.BuildConfig
import com.example.practicapp.retrofit.APIService
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
const val baseUrl = "https://gateway.marvel.com:443/v1/public/characters?ts="

@Module
class DataModule {

    @Provides
    @Singleton
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Binds
    @Singleton
    fun getAPIService(apiService: APIService):APIService{
        return apiService
    }
}