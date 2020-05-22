package com.example.practicapp.repositories

import android.database.Observable
import com.example.practicapp.models.Character
import com.example.practicapp.retrofit.APIService
import retrofit2.Response
import javax.inject.Inject

class RepositoryAPI @Inject constructor(private var apiService: APIService) {

    fun getCharacters(
        ts: Int,
        hash: String,
        apiKey: String
    ): Observable<Response<Character>> {
        return apiService.getCharacters(ts, hash, apiKey)
    }
}