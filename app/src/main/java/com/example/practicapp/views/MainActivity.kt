package com.example.practicapp.views

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.practicapp.App
import com.example.practicapp.R
import com.example.practicapp.repositories.RepositoryAPI
import com.example.practicapp.viewmodels.HeroViewModel
import com.example.practicapp.viewmodels.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var heroViewModel: HeroViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).getComponent().inject(this)
        heroViewModel = ViewModelProviders.of(this, viewModelFactory).get(HeroViewModel::class.java)

    }

}
