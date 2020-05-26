package com.example.practicapp.retrofit


import com.example.practicapp.models.Character
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("character/")
    fun getCharacters(): Call<Character>
}