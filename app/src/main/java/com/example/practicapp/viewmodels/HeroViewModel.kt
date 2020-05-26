package com.example.practicapp.viewmodels


import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicapp.App
import com.example.practicapp.di.DataModule
import com.example.practicapp.di.DataModule_GetAPIServiceFactory.getAPIService
import com.example.practicapp.di.DataModule_GetRetrofitFactory.getRetrofit
import com.example.practicapp.di.ModuleApp
import com.example.practicapp.di.ModuleApp_ProvideContextFactory
import com.example.practicapp.models.Character
import com.example.practicapp.repositories.RepositoryAPI
import com.google.gson.Gson
import com.squareup.moshi.Moshi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class HeroViewModel @Inject constructor(var repositoryAPI: RepositoryAPI): ViewModel() {
    private var dataModule = DataModule()
    val retrofit = dataModule.getRetrofit()
    val service = dataModule.getAPIService(retrofit)
    private val disposable = CompositeDisposable()
    private val responseCharacters = MutableLiveData<Response<ArrayList<Character>>>()


    fun getCharactersMarvel(ts: Int,hash: String, apiKey: String){

        service.getCharacters( hash, apiKey).enqueue(object : Callback<Character>{
            override fun onFailure(call: Call<Character>, t: Throwable) {
                Log.d( "fallo :","Response:${t.message} ${Gson().toJson(call.request().body())}")

            }

            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                val codigo = response.code()
                val errorbody =  response.errorBody()

                val message = response.message()
                Log.d( "exito :","Response: ${Gson().toJson(response.body())} codigo: $codigo")
                if (codigo>400){
                    Log.d("error body:", "$errorbody")
                    Log.d("error message", "$message")
                }
            }

        })

    }

    override fun onCleared() {
        disposable.clear()
    }

}


