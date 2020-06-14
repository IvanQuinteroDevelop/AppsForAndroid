package com.example.practicapp.views

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.practicapp.App
import com.example.practicapp.R
import com.example.practicapp.adapter.CharactersAdapter
import com.example.practicapp.models.Result
import com.example.practicapp.viewmodels.MyViewModel
import com.example.practicapp.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener, SearchView.OnCloseListener {
    @Inject
    lateinit var myViewModel: MyViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var mResult : List<Result>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).getComponent().inject(this)
        myViewModel = ViewModelProvider(this, viewModelFactory).get(MyViewModel::class.java)

        myViewModel.getCharacters()
        myViewModel.resultResponse().observe(this,
            Observer { theResponse -> showCharacters(theResponse)})
        //val refresh = findViewById<SwipeRefreshLayout>(R.id.refresh_character)
        refresh_character.setOnRefreshListener {
            myViewModel.getCharacters()
            refresh_character.isRefreshing = false
        }
    }


    private fun showCharacters(responseList: List<Result>){
        val recycler = findViewById<RecyclerView>(R.id.recycler)

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 3)
        recycler.setHasFixedSize(true)
        recycler.layoutManager = layoutManager
        mResult = responseList
        val adapter = CharactersAdapter(mResult, this)
        recycler.adapter = adapter
        //val mySearch = findViewById<SearchView>(R.id.mySearch)
        mySearch.setOnQueryTextListener(this)
        mySearch.setOnCloseListener(this)

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        filterCharacter(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        filterCharacter(newText)
        return false
    }

    private fun filterCharacter(query: String?){
        val name: String
        if (query != null && query.isNotEmpty()) {
             name = query.trim()
            myViewModel.searchCharacters(name)
        }else myViewModel.getCharacters()
    }

    override fun onClose(): Boolean {
        return true
    }
}
