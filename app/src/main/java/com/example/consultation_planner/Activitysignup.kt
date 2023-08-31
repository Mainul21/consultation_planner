package com.example.consultation_planner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.consultation_planner.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Activitysignup : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var type:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.radioButtonType.setOnCheckedChangeListener { group, CheckedId ->
            when (CheckedId) {
                R.id.radioButton_faculty -> {
                     type = "faculty"


                }

                R.id.radioButton_student -> {
                     type = "student"

                }
                }}

        binding.buttonSignup.setOnClickListener() {
            val email = binding.EditTextSignupEmail.text.toString()
            val pass = binding.EditTextSignupPassword.text.toString()
            val confirmpass = binding.EditTextSignupConfirmpass.text.toString()





            if (email.isNotEmpty() && pass.isNotEmpty() && confirmpass.isNotEmpty()) {
                if (pass == confirmpass) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val userName = binding.edittextUsername.text.toString()
//                           sending data to database
                            database = FirebaseDatabase.getInstance().getReference("Users")
                            val User = User(email, type, userName)
                            database.child(userName).setValue(User)
                            Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
                            intent = Intent(this, ActivityLogIn::class.java)
                            startActivity(intent)

                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }else {
                    Toast.makeText(this, "password doesn't match", Toast.LENGTH_SHORT).show()
                }
            } else{
                Toast.makeText(this, "Empty Fleilds Not accepted", Toast.LENGTH_SHORT).show()
            }

        }
    }
}

