package com.example.practicapp.retrofit


import com.example.practicapp.models.Character
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("characters?ts=1")
    fun getCharacters(
       // @Path("ts") ts:Int,
        @Query("hash") hash:String,
        @Query("apikey") apikey:String
        ): Call<Character>
}