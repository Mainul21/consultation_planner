package com.example.consultation_planner

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class activity_studentFinalPage : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_final_page)

        val name = findViewById<TextView>(R.id.textView_faculty)
        val date = findViewById<TextView>(R.id.textView_date)
        val time = findViewById<TextView>(R.id.textView_time1)
        val intent = intent
        val userName = intent.getStringExtra("userName")
        database = FirebaseDatabase.getInstance().getReference("student")
        if (userName != null) {
            database.child(userName).get().addOnSuccessListener {
                name.text = "Faculty: "+ it.child("faculty").value.toString()
                date.text = "Faculty: "+ it.child("date").value.toString()
                time.text = "Faculty: "+ it.child("time").value.toString()
            }
        }




    }
}