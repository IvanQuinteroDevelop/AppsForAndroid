package com.example.practicapp.viewmodels

import androidx.lifecycle.ViewModel
import com.example.practicapp.repositories.RepositoryAPI
import io.reactivex.disposables.CompositeDisposable

class HeroViewModel(var repositoryAPI: RepositoryAPI): ViewModel() {
private val disposable = CompositeDisposable()
}