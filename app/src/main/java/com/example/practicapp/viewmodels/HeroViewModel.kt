package com.example.practicapp.viewmodels


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicapp.repositories.RepositoryAPI
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.practicapp.models.Character
import javax.inject.Inject

class HeroViewModel @Inject constructor(var repositoryAPI: RepositoryAPI): ViewModel() {
    private val responseCharacters = MutableLiveData<Character>()



    fun getCharacters(){

        repositoryAPI.getCharacters().enqueue(object : Callback<Character>{
            override fun onFailure(call: Call<Character>, t: Throwable) {
                Log.d( "fallo :","Response:${t.message} || ${Gson().toJson(call.request().body())}")
                //TODO utilizar Moshi para parsear el json
                //val adapter: JsonAdapter<Character> = Moshi().adapter(Character::class.java)
            }
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                
                val code = response.code()
                val error =  response.errorBody()
                val message = response.message()
                if (response.isSuccessful){
                    responseCharacters.postValue(response.body())
                    Log.d( "Peticion exitosa","Response: ${response.body()} codigo: $code")
                }else{
                    Log.d("error body:", "$error code: $code")
                    Log.d("error message", message)
                }
            }
        })
    }

}


