package com.example.practicapp.di

import androidx.lifecycle.ViewModelProvider
import com.example.practicapp.repositories.RepositoryAPI
import com.example.practicapp.retrofit.APIService
import com.example.practicapp.viewmodels.ViewModelFactory
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
class DataModule {

    @Provides
    @Singleton
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //TODO Moshiconverterfactory
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun getAPIService(retrofit: Retrofit):APIService{
        return retrofit.create(APIService::class.java)
    }

    @Provides
    @Singleton
    fun getViewModelFactory(repositoryAPI: RepositoryAPI): ViewModelProvider.Factory {
        return ViewModelFactory(repositoryAPI)
    }

    @Provides
    @Singleton
    fun getRepository(apiService: APIService):RepositoryAPI{
        return RepositoryAPI(apiService)
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }
}