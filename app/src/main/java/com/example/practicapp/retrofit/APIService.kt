package com.example.practicapp.retrofit

import android.database.Observable
import com.example.practicapp.models.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface APIService {

    @GET("characters?ts={ts}&hash={hash}&apikey={apikey}")
    fun getCharacters(
        @Path("ts") ts:Int,
        @Path("hash") hash:String,
        @Path("apiKey") apiKey:String
        ): Observable<Response<Character>>
}