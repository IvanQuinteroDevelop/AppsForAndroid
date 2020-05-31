package com.example.practicapp.repositories

import android.util.Log
import com.example.practicapp.db.Dao
import com.example.practicapp.models.Character
import com.example.practicapp.retrofit.APIService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import com.example.practicapp.models.Result

class Repository @Inject constructor(private var apiService: APIService, private var dao: Dao) {

    fun getCharacters(): List<Result> {
        refreshCharacters()
        return dao.getCharactersFromDB()
    }
    fun refreshCharacters(){
        apiService.getCharacters().enqueue(object : Callback<Character> {
            override fun onFailure(call: Call<Character>, t: Throwable) {
                Log.d( "fallo :","Response:${t.message} || ${Gson().toJson(call.request().body())}")
                //TODO utilizar Moshi para parsear el json
                //val adapter: JsonAdapter<Character> = Moshi().adapter(Character::class.java)
            }
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                if (response.isSuccessful){
                    dao.deleteCharacters()
                    dao.insertCharacters(response.body()!!.results)
                }else{ Log.d("error message", response.message()) }
            }
        })
    }

    fun filterCharacters(name: String):List<Result>{
        return dao.searchCharacter(name)
    }
}