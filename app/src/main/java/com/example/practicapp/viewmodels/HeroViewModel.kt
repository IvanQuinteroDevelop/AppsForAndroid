package com.example.practicapp.viewmodels

import androidx.lifecycle.ViewModel
import com.example.practicapp.repositories.RepositoryAPI
import javax.inject.Inject

class HeroViewModel @Inject constructor(var repositoryAPI: RepositoryAPI): ViewModel() {

}