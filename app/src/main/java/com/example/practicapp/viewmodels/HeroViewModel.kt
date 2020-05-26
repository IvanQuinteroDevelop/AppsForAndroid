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
import com.example.practicapp.models.Result
import javax.inject.Inject

class HeroViewModel @Inject constructor(var repositoryAPI: RepositoryAPI): ViewModel() {

    private val responseCharacters = MutableLiveData<List<Result>>()


    fun getCharacters(){
        repositoryAPI.getCharacters().enqueue(object : Callback<Character>{
            override fun onFailure(call: Call<Character>, t: Throwable) {
                Log.d( "fallo :","Response:${t.message} || ${Gson().toJson(call.request().body())}")
                //TODO utilizar Moshi para parsear el json
                //val adapter: JsonAdapter<Character> = Moshi().adapter(Character::class.java)
            }
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                val myResults = response.body()!!.results
                if (response.isSuccessful){
                    responseCharacters.postValue(myResults)
                }else{ Log.d("error message", response.message()) }
            }
        })
    }

    fun resultResponse(): MutableLiveData<List<Result>> {
        return responseCharacters
    }

}


