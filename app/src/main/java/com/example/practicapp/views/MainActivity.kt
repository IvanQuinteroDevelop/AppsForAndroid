package com.example.practicapp.views

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practicapp.App
import com.example.practicapp.R
import com.example.practicapp.models.Character
import com.example.practicapp.retrofit.APIService
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).getComponent().inject(this)


    }

}
