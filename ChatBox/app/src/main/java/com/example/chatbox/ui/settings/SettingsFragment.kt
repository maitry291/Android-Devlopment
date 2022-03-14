package com.example.chatbox.ui.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.example.chatbox.ProfilePhotoActivity
import com.example.chatbox.R
import com.example.chatbox.databinding.FragmentSettingsBinding
import com.example.chatbox.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val myRef = Firebase.database.getReference("Users")
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        root.findViewById<AppCompatButton>(R.id.button)?.setOnClickListener {
            startActivity(Intent(activity, ProfilePhotoActivity::class.java))
        }

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (snap in snapshot.children) {
                    val model = snap.getValue(User::class.java)
                    if (model != null && snap.key == FirebaseAuth.getInstance().currentUser?.uid) {
                        Picasso.get().load(Uri.parse(model.profile))
                            .into(root.findViewById<ImageView>(R.id.settings_profile))
                    }
                    if(snap.key == FirebaseAuth.getInstance().currentUser?.uid && model?.profile == ""){
                        root.findViewById<ImageView>(R.id.settings_profile).setImageResource(R.drawable.basic_user)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
