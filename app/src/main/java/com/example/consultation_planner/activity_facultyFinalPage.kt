package com.example.consultation_planner

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import student_database

class activity_facultyFinalPage : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faculty_final_page)
        val facultyview = findViewById<TextView>(R.id.textView_studentname)
        val intent = intent
        val userName = intent.getStringExtra("userName")

        database = FirebaseDatabase.getInstance().getReference("student")

        val query = database.orderByChild("faculty").equalTo(userName)

        query.addValueEventListener(object : ValueEventListener{
            @SuppressLint("SetTextI18n")
            override fun onDataChange(snapshot: DataSnapshot) {
                val studentWithSameFaculty = mutableListOf<String>()
                val stringBuilder = StringBuilder()
                for(childSnapshot in snapshot.children){
                    val student = childSnapshot.getValue(student_database::class.java)
                    student?.userName?.let{

                        stringBuilder.append(it+"\n")
                    }
                }

                facultyview.text = "Students:\n$stringBuilder"
            }



            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(this, "databaseError", Toast.LENGTH_SHORT).show()
            }
        })

    }
}