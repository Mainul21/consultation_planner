package com.example.consultation_planner

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class faculty : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facultypage)
        val intent = intent
        val username = intent.getStringExtra("userName")
        val title = findViewById<TextView>(R.id.textView_facultypage_title)
        title.text = "Add Your time Slot $username"
        val slot1datEt = findViewById<EditText>(R.id.editText_slot1_date)
        val slot1timeEt = findViewById<EditText>(R.id.editText_slot1_time)
        val slot2dateEt = findViewById<EditText>(R.id.editText_slot2_date)
        val slot2timeEt = findViewById<EditText>(R.id.editText_slot2_time)
        val btn_add = findViewById<Button>(R.id.button_add_faculty)
        val btn_next = findViewById<Button>(R.id.button_faculty_next)




        btn_add.setOnClickListener() {
            val slot1date = slot1datEt.text.toString()
            val slot1time = slot1timeEt.text.toString()
            val slot2date = slot2dateEt.text.toString()
            val slot2time = slot2timeEt.text.toString()
            database = FirebaseDatabase.getInstance().getReference("faculty")
            val slot = faculty_database(username, slot1date, slot1time, slot2date, slot2time)
            if (username != null) {
                database.child(username).setValue(slot).addOnSuccessListener {
                    Toast.makeText(this, "Successfully Added", Toast.LENGTH_SHORT).show()
                    recreate()
                }

            } else {
                Toast.makeText(username, "username is null", Toast.LENGTH_SHORT).show()
            }
        }

        btn_next.setOnClickListener(){
            val intent = Intent(this, activity_facultyFinalPage::class.java)
            intent.putExtra("userName",username)
            startActivity(intent)
        }



    }
}