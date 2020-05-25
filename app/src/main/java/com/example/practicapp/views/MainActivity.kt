package com.example.practicapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.practicapp.App
import com.example.practicapp.R
import com.example.practicapp.models.Character
import com.example.practicapp.models.Thumbnail
import com.example.practicapp.viewmodels.HeroViewModel
import com.example.practicapp.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var heroViewModel: HeroViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var character: Character
    lateinit var thumbnail: Thumbnail
    private val apiKey = "9915000dba318cb03bf998b69e35d00c"
    private val ts = 1
    private val hash = "72032c29aad04b597b388eb275e2d172"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).getComponent().inject(this)
        heroViewModel = ViewModelProvider(this, viewModelFactory).get(HeroViewModel::class.java)

        thumbnail = Thumbnail("url", "jpg")
        character = Character("Spider man", thumbnail )
        txName.text = "Bienvenido ${character.name}"
        heroViewModel.getCharactersMarvel(ts, hash, apiKey)

    }

}
