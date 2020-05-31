package com.example.practicapp.viewmodels

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.practicapp.repositories.Repository
import javax.inject.Inject

class ViewModelFactory @Inject constructor(var repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(@NonNull modelClass: Class<T>): T {
        return MyViewModel(repository) as T
    }

}