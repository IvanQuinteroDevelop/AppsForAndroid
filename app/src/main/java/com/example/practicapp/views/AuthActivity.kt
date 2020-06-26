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
import com.example.practicapp.utilities.Preferences
import com.example.practicapp.viewmodels.MyViewModel
import com.example.practicapp.viewmodels.ViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_auth.*
import java.lang.Exception
import javax.inject.Inject

class AuthActivity : AppCompatActivity() {
@Inject
lateinit var viewModelFactory: ViewModelFactory
lateinit var myViewModel: MyViewModel
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var userReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        (application as App).getComponent().inject(this)
        myViewModel = ViewModelProvider(this, viewModelFactory).get(MyViewModel::class.java)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference("Database PractiApp")
        databaseReference.setValue("Hello World!!")
        userReference = firebaseDatabase.getReference("Users")
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
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    goToHome()
                    finish()
                } else {
                    Toast.makeText(this, "Error to sign in", Toast.LENGTH_LONG).show()
                }
            }
        }
        progress_circular.visibility = View.GONE

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
                    val user = User(name, email)
                    val id = firebaseAuth.currentUser!!.uid
                    userReference.child(id).setValue(user).addOnCompleteListener {
                        Toast.makeText(this, "Registration complete", Toast.LENGTH_SHORT).show()
                        goToHome()
                    }
                }else{
                    Toast.makeText(this, "Error to register", Toast.LENGTH_LONG).show()
                }
            }
        }
        progress_circular.visibility = View.GONE
    }

    private fun goToHome(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        progress_circular.visibility = View.GONE
        finish()
    }

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser!= null){
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}

