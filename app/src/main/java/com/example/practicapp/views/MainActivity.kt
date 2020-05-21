package com.example.practicapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practicapp.App
import com.example.practicapp.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).getComponent().inject(this)

    }

}
