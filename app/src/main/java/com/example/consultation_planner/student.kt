package com.example.consultation_planner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import student_database

class student : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var dataTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_studentpage)



        database = FirebaseDatabase.getInstance().getReference("faculty")
        dataTextView = findViewById(R.id.textView_slot_show)
        val add = findViewById<Button>(R.id.button_addStudent)
        val next = findViewById<Button>(R.id.button_student_next)
        val facultyEt = findViewById<EditText>(R.id.editText_facultyName)
        val DateEt = findViewById<EditText>(R.id.editText_setDate)
        val timeEt = findViewById<EditText>(R.id.editText_setTime)

        fetchDataFromFirebase()
        add.setOnClickListener() {
            val intent = intent
            val userName = intent.getStringExtra("userName")
            val faculty = facultyEt.text.toString()
            val date = DateEt.text.toString()
            val time = timeEt.text.toString()

            database = FirebaseDatabase.getInstance().getReference("student")
            val student = student_database(userName,faculty, date, time)
            if (userName != null) {
                database.child(userName).setValue(student).addOnSuccessListener {
                    Toast.makeText(this, "Successfully added", Toast.LENGTH_SHORT).show()
                }
            }
        }

        next.setOnClickListener(){
            var intent = intent
            val userName = intent.getStringExtra("userName")
            intent = Intent(this, activity_studentFinalPage::class.java)
            intent.putExtra("userName",userName)
            startActivity(intent)
        }


    }

    private fun fetchDataFromFirebase() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val stringBuilder = StringBuilder()
                for (dataSnapshot in snapshot.children) {
                    val faculty = dataSnapshot.getValue(faculty_database::class.java)
                    if (faculty != null) {
                        stringBuilder.append("Faculty ID: ${faculty.id} \n")
                        stringBuilder.append("${faculty.slot1date} - ")
                        stringBuilder.append(" ${faculty.slot1time} , ")
                        stringBuilder.append("${faculty.slot2date} - ")
                        stringBuilder.append(" ${faculty.slot2time}\n\n")
                    }
                }

                dataTextView.text = stringBuilder.toString()
            }

            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(this, "Unsuccessful Attempt", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
