package com.example.consultation_planner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.consultation_planner.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ActivityLogIn : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.buttonLoginSignup.setOnClickListener() {
            intent = Intent(this, Activitysignup::class.java)
            startActivity(intent)
        }

        binding.buttonLogin.setOnClickListener() {
            val email = binding.editTextLoginEmail.text.toString()
            val pass = binding.EditTextLoginPassword.text.toString()
            val username = binding.edittextUsername.text.toString()
            if (email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener() {
                    if (it.isSuccessful) {
                        database = FirebaseDatabase.getInstance().getReference("Users")
                        database.child(username).get().addOnSuccessListener {
                            if(it.exists()){
                                val type = it.child("acess").value
                                if(type == "faculty"){
                                    intent = Intent(this,faculty::class.java)
                                    intent.putExtra("userName",username)

                                    startActivity(intent)
                                }else if(type == "student"){
                                    intent = Intent(this, student::class.java)
                                    intent.putExtra("userName",username)
                                    startActivity(intent)
                                }
                            }

                        }

                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }

                }
            } else {
                Toast.makeText(this, "empty fields not accepted", Toast.LENGTH_SHORT).show()

            }
        }
    }


}







