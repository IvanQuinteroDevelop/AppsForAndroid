package com.example.practicapp.views

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicapp.App
import com.example.practicapp.R
import com.example.practicapp.adapter.CharactersAdapter
import com.example.practicapp.models.Result
import com.example.practicapp.viewmodels.HeroViewModel
import com.example.practicapp.viewmodels.ViewModelFactory
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var heroViewModel: HeroViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var mResult : List<Result>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).getComponent().inject(this)
        heroViewModel = ViewModelProvider(this, viewModelFactory).get(HeroViewModel::class.java)

        heroViewModel.getCharacters()

        heroViewModel.resultResponse().observe(this,
        Observer { theResponse -> showCharacters(theResponse)})

    }


    private fun showCharacters(responseList: List<Result>){
        val recycler = findViewById<RecyclerView>(R.id.recycler)

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 2)
        recycler.setHasFixedSize(true)
        recycler.layoutManager = layoutManager
        mResult = responseList
        val adapter = CharactersAdapter(mResult, this)

        recycler.adapter = adapter
    }
}
