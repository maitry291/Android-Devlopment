package com.example.chatbox

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.chatbox.models.User
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import java.io.File

class ProfilePhotoActivity : AppCompatActivity() {
     @SuppressLint("UseCompatLoadingForDrawables")

    private lateinit var tempImageUri: Uri
    private var tempImagePath = ""

    //Create a storage reference from our app
    val storage = FirebaseStorage.getInstance()
    var storageRef = storage.reference
    private val auth = FirebaseAuth.getInstance()
    val imageRef = storageRef.child("profileImage")
        .child(auth.currentUser?.uid.toString())
    val db = Firebase.database
    val myRef = db.getReference("Users")

    private val selectPictureLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) {
            findViewById<ImageView>(R.id.preview).setImageURI(it)
            uploadImageUri(it)
            findViewById<ImageView>(R.id.preview).tag = "galleryPictureSelected"
            findViewById<ImageView>(R.id.user_male).setImageResource(R.drawable.user_male)
            findViewById<ImageView>(R.id.user_female).setImageResource(R.drawable.user_female)
        }

    private val cameraLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                findViewById<ImageView>(R.id.preview).setImageURI(tempImageUri)
                uploadImageUri(tempImageUri)
                findViewById<ImageView>(R.id.preview).tag = "cameraPictureSelected"
                findViewById<ImageView>(R.id.user_male).setImageResource(R.drawable.user_male)
                findViewById<ImageView>(R.id.user_female).setImageResource(R.drawable.user_female)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_photo)
        supportActionBar?.hide()

        val maleUser = findViewById<ImageView>(R.id.user_male)
        val femaleUser = findViewById<ImageView>(R.id.user_female)

        maleUser.setOnClickListener {
            when (maleUser.tag) {
                "maleLogo" -> {
                    if (femaleUser.tag == "femaleLogo") {
                        maleUser.setImageResource(R.drawable.user_male_selected)
                        maleUser.tag = "maleLogoSelected"
                        findViewById<ImageView>(R.id.preview).setImageResource(R.drawable.user_male)
                        findViewById<ImageView>(R.id.preview).tag = "maleLogo"
                    }
                    if (femaleUser.tag == "femaleLogoSelected") {
                        femaleUser.setImageResource(R.drawable.user_female)
                        femaleUser.tag = "femaleLogo"
                        maleUser.setImageResource(R.drawable.user_male_selected)
                        maleUser.tag = "maleLogoSelected"
                        findViewById<ImageView>(R.id.preview).setImageResource(R.drawable.user_male)
                        findViewById<ImageView>(R.id.preview).tag = "maleLogo"
                    }
                }
                "maleLogoSelected" -> {
                    maleUser.setImageResource(R.drawable.user_male)
                    maleUser.tag = "maleLogo"
                    findViewById<ImageView>(R.id.preview).setImageResource(R.drawable.basic_user)
                    findViewById<CircleImageView>(R.id.preview).tag = "noPhoto"
                }
            }
        }

        femaleUser.setOnClickListener {
            when (femaleUser.tag) {
                "femaleLogo" -> {
                    if (maleUser.tag == "maleLogo") {
                        femaleUser.setImageResource(R.drawable.user_female_selected)
                        femaleUser.tag = "femaleLogoSelected"
                        findViewById<ImageView>(R.id.preview).setImageResource(R.drawable.user_female)
                        findViewById<ImageView>(R.id.preview).tag = "femaleLogo"
                    }
                    if (maleUser.tag == "maleLogoSelected") {
                        maleUser.setImageResource(R.drawable.user_male)
                        maleUser.tag = "maleLogo"
                        femaleUser.setImageResource(R.drawable.user_female_selected)
                        femaleUser.tag = "femaleLogoSelected"
                        findViewById<ImageView>(R.id.preview).setImageResource(R.drawable.user_female)
                        findViewById<ImageView>(R.id.preview).tag = "femaleLogo"
                    }
                }
                "femaleLogoSelected" -> {
                    femaleUser.setImageResource(R.drawable.user_female)
                    femaleUser.tag = "femaleLogo"
                    findViewById<ImageView>(R.id.preview).setImageResource(R.drawable.basic_user)
                    findViewById<CircleImageView>(R.id.preview).tag = "noPhoto"
                }
            }
        }

        // Layout for bottom sheet dialog
        val view = layoutInflater.inflate(R.layout.bottom_sheet_layout, null)

        // Object of BottomSheetDialog
        val dialog = BottomSheetDialog(this)

        // Opening bottom sheet
        findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener {
            // Assigning layout
            dialog.setContentView(view)
            // Showing bottom sheet
            dialog.show()
        }

        val camera = view.findViewById<ImageView>(R.id.camera)
        val gallery = view.findViewById<ImageView>(R.id.gallery)

        camera.setOnClickListener {

            tempImageUri = FileProvider.getUriForFile(
                this,
                "com.example.chatbox.provider", createImageFile().also {
                    tempImagePath = it.absolutePath
                }
            )
            cameraLauncher.launch(tempImageUri)
            dialog.dismiss()
        }

        gallery.setOnClickListener {
            selectPictureLauncher.launch("image/*")
            dialog.dismiss()
        }

        findViewById<Button>(R.id.button).setOnClickListener {
            val intent1 = Intent(this, MainActivity::class.java)
            if (findViewById<ImageView>(R.id.preview).tag == "cameraPictureSelected" ||
                findViewById<ImageView>(R.id.preview).tag == "galleryPictureSelected" ||
                findViewById<ImageView>(R.id.preview).tag == "maleLogo" ||
                findViewById<ImageView>(R.id.preview).tag == "femaleLogo"
            ) {
                if (findViewById<ImageView>(R.id.preview).tag == "maleLogo") {
                    val uri = Uri.parse("android.resource://com.example.chatbox/drawable/user_male")
                    uploadImageUri(uri)
                } else if (findViewById<ImageView>(R.id.preview).tag == "femaleLogo") {
                    val uri =
                        Uri.parse("android.resource://com.example.chatbox/drawable/user_female")
                    uploadImageUri(uri)
                }
                startActivity(intent1)
            } else {
                Snackbar.make(it, "Please select a profile picture!", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
    private fun createImageFile(): File {
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("temp_image", ".jpg", storageDir)
    }

    private fun uploadImageUri(uri: Uri?) {
        if (uri != null) {
            imageRef.putFile(uri).addOnSuccessListener {
                imageRef.downloadUrl.addOnSuccessListener {
                    Firebase.database.reference.child("Users")
                        .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
                        .child("profile")
                        .setValue(it.toString())
                    Toast.makeText(this, "Image uploaded", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}