package com.example.project

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.project.adapters.Adapter
import com.example.project.models.Registration
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*

class Register : AppCompatActivity() {

    private val db=Firebase.database
    private val auth=FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val spinner=findViewById<Spinner>(R.id.spinner_gender)

        val list2 = arrayListOf("Male", "Female", "OTHER")
        val adapter2 = Adapter(list2, this)
        spinner.adapter = adapter2
        
        personal_info.setOnClickListener {
            val fname=edt_firstname.text.toString()
            val lname=edt_lastname.text.toString()
            val gender=list2[spinner.selectedItemPosition]
            val bdate="12"
            val bio=edt_bio.text.toString()
            val email=edt_email.text.toString()
            val phone=edt_phone.text.toString()

            if(fname==""||lname==""||gender==""||bdate==""||bio==""||email==""||phone==""){
                Toast.makeText(this, "Please Fill all details", Toast.LENGTH_SHORT).show()
            }
            else {
                val user = Registration(fname, lname, gender, bdate, email, bio, phone)

                db.getReference("Registration").child(auth!!.uid).setValue(user)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, UserDetails::class.java))
                    }
            }

        }

    }

}