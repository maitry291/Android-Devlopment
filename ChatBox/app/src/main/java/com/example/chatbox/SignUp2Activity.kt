package com.example.chatbox

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View.VISIBLE
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.example.chatbox.models.User
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.hbb20.CountryCodePicker
import kotlinx.android.synthetic.main.activity_sign_up2.*
import java.util.concurrent.TimeUnit

class SignUp2Activity : AppCompatActivity() {

    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private val auth = FirebaseAuth.getInstance()

    private val db = Firebase.database
    private val myRef = db.getReference("Users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up2)
        supportActionBar?.setLogo(R.drawable.app_logo)
        supportActionBar?.setDisplayUseLogoEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "  Sign Up to ChatBox"
        supportActionBar?.elevation
        supportActionBar?.setBackgroundDrawable(
            AppCompatResources.getDrawable(
                this,
                R.drawable.actionbar_bg
            )
        )

        lateinit var storedVerificationId: String
        lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

        signup2_to_signin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        val edtPhone = findViewById<EditText>(R.id.editTextPhone)
        val btnOTP = findViewById<AppCompatButton>(R.id.btn_getOTP)
        val edtOTP = findViewById<EditText>(R.id.editTextOTP)
        val ccp = findViewById<CountryCodePicker>(R.id.ccp)
        val name = findViewById<EditText>(R.id.signup2_name)
        val signUp2 = findViewById<AppCompatButton>(R.id.btnSignUp2)

        ccp.registerCarrierNumberEditText(edtPhone)

        btnVerify.setOnClickListener {
            if (btnOTP.tag == "verifyOTP") {
                myRef.orderByChild("phone").equalTo(ccp.fullNumberWithPlus)
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {

                            if (ccp.isValidFullNumber) {
                                val options = PhoneAuthOptions.newBuilder(auth)
                                    .setPhoneNumber(ccp.fullNumberWithPlus)       // Phone number to verify
                                    .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                                    .setActivity(this@SignUp2Activity)                 // Activity (for callback binding)
                                    .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
                                    .build()
                                PhoneAuthProvider.verifyPhoneNumber(options)

                                //findViewById<ImageView>(R.id.btnVerify).setImageResource(R.drawable.verify_green)
                            } else {
                                Snackbar.make(
                                    it,
                                    "Enter a valid Phone Number!",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }

                            if (snapshot.exists()) {
                                Toast.makeText(baseContext,"Number already exists, Verifying..Please wait.", Toast.LENGTH_SHORT).show()
                                for (snap in snapshot.children){
                                    val model=snap.getValue(User::class.java)
                                    if(model?.phone==ccp.fullNumberWithPlus){
                                        name.setText(model?.userName)
                                        signUp2.text = "Login"
                                    }
                                }
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            Log.w(TAG, "Failed to read value.", error.toException())
                        }
                    })
            }
        }

        btnOTP.setOnClickListener {
            if (btnOTP.tag == "verifyOTP") {
                val otp = edtOTP.text.trim().toString()
                if (otp.isNotEmpty()) {
                    val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(
                        storedVerificationId, otp
                    )
                    auth.signInWithCredential(credential)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithCredential:success")
                                val user = task.result?.user
                                btnOTP.text = "Verified"
                                btnOTP.tag = "verified"
                                btnOTP.setTextColor(ContextCompat.getColor(this, R.color.green))

                                name.visibility = VISIBLE
                                findViewById<AppCompatButton>(R.id.btnSignUp2).visibility = VISIBLE
                            } else {
                                // Sign in failed, display a message and update the UI
                                Log.w(TAG, "signInWithCredential:failure", task.exception)
                                if (task.exception is FirebaseAuthInvalidCredentialsException) {
                                    Toast.makeText(
                                        this,
                                        task.exception.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                Toast.makeText(this, "Invalid OTP!", Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    Toast.makeText(this, "Invalid OTP!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        findViewById<AppCompatButton>(R.id.btnSignUp2).setOnClickListener {
            if (findViewById<CountryCodePicker>(R.id.ccp).isValidFullNumber &&
                findViewById<EditText>(R.id.signup2_name).text.toString().isNotEmpty()
            ) {
                val model = User()
                model.phone = findViewById<CountryCodePicker>(R.id.ccp).fullNumberWithPlus
                model.userName = findViewById<EditText>(R.id.signup2_name).text.toString()
                model.uid = FirebaseAuth.getInstance().currentUser?.uid.toString()
                myRef.child(auth.uid.toString()).setValue(model)

                val intent = Intent(this@SignUp2Activity, ProfilePhotoActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(baseContext, "All fields are required.", Toast.LENGTH_SHORT).show()
            }
        }
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d(TAG, "onVerificationCompleted:$credential")
            }

            override fun onVerificationFailed(e: FirebaseException) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e)

                if (e is FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(baseContext, "Invalid request", Toast.LENGTH_SHORT).show()
                } else if (e is FirebaseTooManyRequestsException) {
                    Toast.makeText(baseContext, "The SMS quota for the project has been exceeded", Toast.LENGTH_SHORT).show()
                }

                // Show a message and update the UI
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:$verificationId")

                // Save verification ID and resending token so we can use them later
                storedVerificationId = verificationId
                resendToken = token
                findViewById<ImageView>(R.id.btnVerify).setImageResource(R.drawable.verify_green)
                findViewById<RelativeLayout>(R.id.relativeLayout).visibility = VISIBLE
            }
        }
    }

    // For creating account with phone number(authentication)
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                    Toast.makeText(this, "Unexpected Error", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun ifNumberAlreadyExists(number:String) : Boolean {
        var c : Boolean=false
        myRef.orderByChild("phone").equalTo(number).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                c = snapshot.exists()
                Log.w(TAG,"value of c $c")
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })

        return c
    }

    private fun returnTrue(): Boolean {
        return true
    }
}