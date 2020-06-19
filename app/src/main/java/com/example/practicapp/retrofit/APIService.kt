package com.example.practicapp.retrofit


import com.example.practicapp.models.Character
import retrofit2.Call
import retrofit2.http.GET

interface APIService {

    @GET("character/?page=8")
    fun getCharacters(): Call<Character>
}