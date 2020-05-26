package com.example.practicapp.repositories

import com.example.practicapp.models.Character
import com.example.practicapp.retrofit.APIService
import retrofit2.Call
import javax.inject.Inject

class RepositoryAPI @Inject constructor(private var apiService: APIService) {

    fun getCharacters(
        ts: Int,
        hash: String,
        apiKey: String
    ): Call<Character> {
        return apiService.getCharacters( hash, apiKey)
    }
}