package com.example.practicapp.repositories

import android.database.Observable
import com.example.practicapp.models.Character
import com.example.practicapp.retrofit.APIService
import retrofit2.Response
import java.util.*

class RepositoryAPI(var apiService: APIService) {

    fun getCharacters(
        ts: Int,
        hash: String,
        apikey: String
    ): Observable<Response<Character>> {
        return apiService.getCharacters(ts, hash, apikey)
    }
}