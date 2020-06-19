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
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*
import java.lang.Exception
import javax.inject.Inject

class AuthActivity : AppCompatActivity() {
@Inject
lateinit var viewModelFactory: ViewModelFactory
lateinit var myViewModel: MyViewModel
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        (application as App).getComponent().inject(this)
        myViewModel = ViewModelProvider(this, viewModelFactory).get(MyViewModel::class.java)

        firebaseAuth = FirebaseAuth.getInstance()
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
        if (valid) {
            progress_circular.visibility = View.VISIBLE
            val user = myViewModel.getUser(email, password)
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    if (user.email == email && user.password == password) {
                        goToHome(user)
                    }
                } else {
                    Toast.makeText(this, "Invalid user", Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    fun onClickRegister(view: View){
        val edName = findViewById<EditText>(R.id.ed_name_register)
        val edEmail = findViewById<EditText>(R.id.ed_register_email)
        val edPassword = findViewById<EditText>(R.id.ed_register_pass)
        val edPasswordConfirmation = findViewById<EditText>(R.id.ed_confirm_pass)
        val name = edName.text.toString().trim()
        val email = edEmail.text.toString().trim()
        val password = edPassword.text.toString().trim()
        val confirmPassword = edPasswordConfirmation.text.toString().trim()

        var valid = true
        when{
            name.isEmpty() -> {edName.error = getString(R.string.required_name); valid = false}
            email.isEmpty() -> {edEmail.error = getString(R.string.required_email); valid = false}
            password.isEmpty() -> {edPassword.error = getString(R.string.required_password); valid = false}
            confirmPassword.isEmpty() -> {edPasswordConfirmation.error = getString(R.string.required_password); valid = false}
            password.length<8 -> {edPassword.error = getString(R.string.invalid_password); valid = false}
            confirmPassword!= password -> {edPasswordConfirmation.error = getString(R.string.must_be_equals); valid = false}
        }
        if (valid){
            progress_circular.visibility = View.VISIBLE
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                if(it.isSuccessful){
                    val user = User(name, email, password)
                    
                    myViewModel.insertUser(user)
                    goToHome(user)
                }else{
                    Toast.makeText(this, "Error to register", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun goToHome(user: User){
        val intent = Intent(this, MainActivity::class.java)
        Toast.makeText(this, "Welcome ${user.nameUser}", Toast.LENGTH_LONG).show()
        intent.putExtra("user", user)
        startActivity(intent)
        progress_circular.visibility = View.GONE
    }
}
