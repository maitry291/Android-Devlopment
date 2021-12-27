package com.example.whatsappclone

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.whatsappclone.models.UserInfo
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login_screen.*
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class LoginScreen : AppCompatActivity() {

    private val auth=FirebaseAuth.getInstance()
    var resendToken :String?=""
    private val database=Firebase.database
    val userRef=database.getReference("UserChat")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        supportActionBar?.hide()

        //when we click on get verification code button
        getcode.setOnClickListener {
            if(number.text.toString().isEmpty()){
                number.setError("Please enter number")
                return@setOnClickListener
            }
          //length of text must be 13 including 10 is length of number and +91 country code.
            if(number.text.toString().length!=13) {
               Toast.makeText(this, "Enter valid number with country code", Toast.LENGTH_SHORT).show()
               number.requestFocus();
            }
            else {
                code.visibility=View.VISIBLE
                btn.visibility=View.VISIBLE
                sendVerificationCode()  //made by me this will do all task for verification
            }
        }
    }

    //if user already exists then we will navigate to main activity
    override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser? =auth.currentUser
        updateUI(currentUser)
    }
    private fun updateUI(user:FirebaseUser?){
        if(user==null){
           return
        }
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

    private fun sendVerificationCode(){

        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {  //object of abstract class
            // PhoneAuthProvider.OnVerificationStateChangedCallbacks(). So we need to implement below methods

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d(TAG, "onVerificationCompleted:$credential")

                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                 Log.w(TAG, "onVerificationFailed", e)
                //this if else block will show us the error in form of toast but not needed as i already made text view to show error
 if (e is FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(this@LoginScreen, e.toString(), Toast.LENGTH_SHORT).show()
                } else if (e is FirebaseTooManyRequestsException) {
                    Toast.makeText(this@LoginScreen, e.toString(), Toast.LENGTH_SHORT).show()
                }

                progressBar.visibility=View.GONE
                error.text=e.toString()  //this will show the exception on log in screen of mobile.
                Toast.makeText(this@LoginScreen, "Verification failed", Toast.LENGTH_SHORT).show()

            }

            override fun onCodeSent(verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:${verificationId.toString()}")
                super.onCodeSent(verificationId, token)
                progressBar.visibility = View.GONE

                // Save verification ID and resending token so we can use them later
                resendToken = token.toString()

                //when we click on sign in button progress bar will be active and verification starts.
                btn.setOnClickListener {
                    progressBar.visibility = View.VISIBLE
                    verifySignInCode(verificationId.toString(),code.text.toString())  //method made by me
                    Toast.makeText(this@LoginScreen, "verifying...", Toast.LENGTH_SHORT).show()
                }
            }

        }

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number.text.toString(),        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                callbacks)        // OnVerificationStateChangedCallbacks

    }

    private fun verifySignInCode(codeSent:String,otpText:String){
        val credential:PhoneAuthCredential=PhoneAuthProvider.getCredential(codeSent,otpText)
        signInWithPhoneAuthCredential(credential)
    }
   //this methods verifies the verification id with code written in edit text using credential.
    // PhoneAuthCredential extends AuthCredential. implements Cloneable.
   // Wraps phone number and verification information for authentication purposes.
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->

                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                   // Log.d(TAG, "signInWithCredential:success")
                    Toast.makeText(this, "Sign in successful", Toast.LENGTH_SHORT).show()   

                    uploadUserInDatabase()

                    progressBar.visibility = View.GONE
                    //this will take us to main activity.
                    startActivity(Intent(this, MainActivity::class.java))

                }
                else {
                    // Sign in failed, display a message and update the UI
                    error.text=task.exception?.toString()
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, "code invalid", Toast.LENGTH_SHORT).show()
                    }
                    // Update UI
                }
            }

    }

    private fun uploadUserInDatabase() {
        val name=findViewById<EditText>(R.id.name)
        val msg=findViewById<TextView>(R.id.lastMessage)
        val time=findViewById<TextView>(R.id.timestamp)

        val user:UserInfo= UserInfo()
        user.UserName=name.text.toString()
        user.lastmsg="Last msg"
        user.userId=auth.uid.toString()
        user.phoneNumber=number.text.toString()
        user.timestamp=SimpleDateFormat("hh:mm:ss").format(Date()).toString()

        userRef.child(auth.uid.toString()).setValue(user)
    }
}
