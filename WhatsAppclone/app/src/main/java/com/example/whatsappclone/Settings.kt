package com.example.whatsappclone

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.whatsappclone.models.UserInfo
import com.google.android.gms.auth.api.signin.internal.Storage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_login_screen.*
import kotlinx.android.synthetic.main.activity_settings.*

class Settings : AppCompatActivity() {

    private val ref = Firebase.database.getReference("UserChat")
    private val currentUSer = FirebaseAuth.getInstance().currentUser
    private val storage = FirebaseStorage.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        urnumber.append(currentUSer?.phoneNumber.toString())

        //this will save the user name and about from edittext to firebase
        save.setOnClickListener {
            if (urname.text.isNotEmpty() || about.text.isNotEmpty()) {
                if (urname.text.isEmpty()&&about.text.isNotEmpty()){
                    ref.child(currentUSer?.uid.toString()).child("about")
                        .setValue(about.text.toString()).addOnSuccessListener { }
                    ref.child(currentUSer?.uid.toString()).child("userName")
                        .setValue(urname.hint.toString()).addOnSuccessListener { }
                }
                if (about.text.isEmpty()&&urname.text.isNotEmpty()){
                    ref.child(currentUSer?.uid.toString()).child("userName")
                        .setValue(urname.text.toString()).addOnSuccessListener { }
                }else {
                    ref.child(currentUSer?.uid.toString()).child("about")
                        .setValue(about.text.toString()).addOnSuccessListener { }
                    ref.child(currentUSer?.uid.toString()).child("userName")
                        .setValue(urname.text.toString()).addOnSuccessListener { }
                }
                Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this, "Please fill atleast one", Toast.LENGTH_SHORT).show()
        }

        //this will show the image,user name and about in setting activity my profile view from the database if value is assigned.
        ref.child(currentUSer?.uid.toString())
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue(UserInfo::class.java)
                    if (user != null) {
                        if (user.profilePic?.isEmpty() == false)
                            Picasso.get().load(user.profilePic).into(userImage)
                        urname.setHint(user.UserName)
                        if (user.about?.isEmpty() == false)
                            about.setHint(user.about)
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        userImage.setOnClickListener {
            checkPermissionToUploadImage()
        }
    }

    //will check the permission in the manifest i.e. permitted or not.
    private fun checkPermissionToUploadImage() {
        if (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) &&
                        (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
            } else {
                TODO("VERSION.SDK_INT < M")
            }
        ) {

            val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            val permission2 = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permission, 1001)
                requestPermissions(permission2, 1002)
            }

        } else {
            pickImage()
        }

    }

    private fun pickImage() {
        val i = Intent(Intent.ACTION_PICK)
        i.type = "image/*"
        startActivityForResult(i, 1000)  //to use this we have to override onActivityResult method.
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 1000) {
            data?.data.let { uri ->
                //sets image on userImage view
                userImage.setImageURI(uri)
                //this will store the image in the storage section of firebase.
                val imageRef =
                    storage.reference.child("ProfileImage").child(currentUSer?.uid.toString())
                //this will save/set the image in the UserInfo class profilePic parameter in database.
                if (uri != null) {
                    imageRef.putFile(uri).addOnSuccessListener {
                        imageRef.downloadUrl.addOnSuccessListener {
                            ref.child(currentUSer?.uid.toString()).child("profilePic")
                                .setValue(it.toString())
                            Toast.makeText(this, "Image uploaded", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}