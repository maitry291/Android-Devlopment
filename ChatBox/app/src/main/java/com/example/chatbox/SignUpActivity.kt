package com.example.chatbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import com.example.chatbox.models.User
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.btnSignUp
import kotlinx.android.synthetic.main.activity_sign_up.signup_name
import kotlinx.android.synthetic.main.activity_sign_up.signup_password
import kotlinx.android.synthetic.main.activity_sign_up.signup_password2

class SignUpActivity : AppCompatActivity() {
    val auth = FirebaseAuth.getInstance()
    val db = Firebase.database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.setLogo(R.drawable.app_logo)
        supportActionBar?.setDisplayUseLogoEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "  Sign Up to ChatBox"
        supportActionBar?.elevation
        supportActionBar?.setBackgroundDrawable(AppCompatResources.getDrawable(this,R.drawable.actionbar_bg))
        val myRef = db.getReference("Users")

        btnSignUp.setOnClickListener {
            auth.createUserWithEmailAndPassword(
                signup_email.text.toString(),
                signup_password.text.toString()
            )
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        // Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser

                        val model = User()
                        model.userName = signup_name.text.toString()
                        model.email = signup_email.text.toString()
                        model.passwd = signup_password2.text.toString()
                        model.uid = FirebaseAuth.getInstance().currentUser?.uid.toString()

                        myRef.child(auth.uid.toString()).setValue(model)

                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        //Log.w(TAG, "createUserWithEmail:failure", task.exception)

                        Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                        //updateUI(null)
                    }
                }
        }

        signup_phone.setOnClickListener {
            startActivity(Intent(this,SignUp2Activity::class.java))
        }

        signup_to_signin.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }


    }

    private fun updateUI(user: FirebaseUser?) {
        startActivity(Intent(this, ProfilePhotoActivity::class.java))
    }

}