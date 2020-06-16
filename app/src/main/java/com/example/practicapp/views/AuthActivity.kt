package com.example.practicapp.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.practicapp.App
import com.example.practicapp.R
import com.example.practicapp.models.User
import com.example.practicapp.viewmodels.MyViewModel
import com.example.practicapp.viewmodels.ViewModelFactory
import java.lang.Exception
import javax.inject.Inject

class AuthActivity : AppCompatActivity() {
@Inject
lateinit var viewModelFactory: ViewModelFactory
lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        (application as App).getComponent().inject(this)
        myViewModel = ViewModelProvider(this, viewModelFactory).get(MyViewModel::class.java)
        val user = User(1, "Carlos", "carlos@gmail.com", "A123123a")
        myViewModel.insertUser(user)
    }


    fun onClickLogin(view: View) {
        val edEmail = findViewById<EditText>(R.id.ed_email)
        val edPassword = findViewById<EditText>(R.id.ed_password)
        val email = edEmail.text.toString()
        val password = edPassword.text.toString()

        var valid = true
        when{
            email.isEmpty() -> {edEmail.error = getString(R.string.required_email); valid = false}
            password.isEmpty() -> {edPassword.error = getString(R.string.required_password); valid = false}
            password.length<8 -> {edPassword.error = getString(R.string.invalid_password); valid = false}
        }
        if(valid){
            try {
                val user = myViewModel.getUser(email, password)
                if (user.email == email && user.password == password){
                    val intent = Intent(this, MainActivity::class.java)
                    Toast.makeText(this, "Welcome ${user.nameUser}", Toast.LENGTH_LONG).show()
                    startActivity(intent)
                }
            }catch (e: Exception){
                Log.d("tag ", "Error ${e.message}")
                Toast.makeText(this, "Invalid user", Toast.LENGTH_LONG).show()
            }

        }
    }

}
