package com.example.practicapp.views

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicapp.App
import com.example.practicapp.R
import com.example.practicapp.adapter.CharactersAdapter
import com.example.practicapp.models.Result
import com.example.practicapp.models.User
import com.example.practicapp.utilities.Preferences
import com.example.practicapp.viewmodels.MyViewModel
import com.example.practicapp.viewmodels.ViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener, SearchView.OnCloseListener {
    @Inject
    lateinit var myViewModel: MyViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var mResult : List<Result>
    lateinit var user: User
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var userReference: DatabaseReference
    private lateinit var firebaseDatabase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).getComponent().inject(this)
        myViewModel = ViewModelProvider(this, viewModelFactory).get(MyViewModel::class.java)
        setSupportActionBar(findViewById(R.id.tool_bar_admin))
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        userReference = firebaseDatabase.getReference("Users")

        myViewModel.getCharacters()
        myViewModel.resultResponse().observe(this,
            Observer { theResponse -> showCharacters(theResponse)})
        refresh_character.setOnRefreshListener {
            myViewModel.getCharacters()
            refresh_character.isRefreshing = false
        }
        infoUser()

    }

    private fun infoUser(){
        val key = firebaseAuth.currentUser!!.uid
        userReference.child(key).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(App.context, "onCancelled: ${p0.message}", Toast.LENGTH_SHORT).show()
            }
            override fun onDataChange(p0: DataSnapshot) {
                user = p0.getValue(User::class.java)!!
                Preferences.saveUser(this@MainActivity, user)
                tittle_main.text = user.name
            }
        })
    }

    private fun showCharacters(responseList: List<Result>){
        val recycler = findViewById<RecyclerView>(R.id.recycler)

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 2)
        recycler.setHasFixedSize(true)
        recycler.layoutManager = layoutManager
        mResult = responseList
        val adapter = CharactersAdapter(mResult, this)
        recycler.adapter = adapter
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.sign_out_btn -> signOut()
            R.id.action_settings -> Toast.makeText(this, "Not available", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun signOut(){
        showConfirmation()
    }

    private fun showConfirmation(){
        val builder = AlertDialog.Builder(this, 0)
        builder.setMessage(getString(R.string.are_you_sure))
        builder.setNegativeButton(getString(R.string.cancel), null)
        builder.setPositiveButton(getString(R.string.yes) ){ _, _ ->
            Preferences.clearData(this)
            firebaseAuth.signOut()
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            finish()
        }
        builder.create().show()
    }
}
