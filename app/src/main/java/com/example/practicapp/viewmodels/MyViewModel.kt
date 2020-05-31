package com.example.practicapp.viewmodels


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicapp.repositories.Repository
import com.example.practicapp.models.Result
import javax.inject.Inject

class MyViewModel @Inject constructor(var repository: Repository): ViewModel() {

    private val responseCharacters = MutableLiveData<List<Result>>()

    fun getCharacters(){
        responseCharacters.postValue(repository.getCharacters())
    }

    fun searchCharacters(name: String){
        responseCharacters.postValue(repository.filterCharacters(name))
    }
    fun resultResponse(): MutableLiveData<List<Result>> {
        return responseCharacters
    }

}


