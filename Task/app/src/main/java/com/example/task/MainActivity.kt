package com.example.task

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.task.fragments.FragmentOne
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //activity to fragment (to place fragment in activity)

        button.setOnClickListener {
            // Navigation.findNavController(button).navigate(R.id.action_drawer_settings_to_drawer_faq)
            //to set fragment in activity we need a layout in main activity in which we can set our fragment
            val frag = FragmentOne()
            val fragmentManager: FragmentManager=supportFragmentManager
                val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.ll, frag)
                fragmentTransaction.commit()

        }
    }

    override fun onBackPressed() {
        var builder=AlertDialog.Builder(this)
        builder.setMessage("Do you want to exit ?")

        builder.setTitle("Alert..!")
        builder.setCancelable(false)

        builder.setPositiveButton("Yes",DialogInterface.OnClickListener { dialogInterface, i ->
            finishAffinity()
        })

        builder.setNegativeButton("No",DialogInterface.OnClickListener { dialogInterface, i ->
            dialogInterface.cancel()
        })

        val alertDialog=builder.create()

        alertDialog.show()

    }
}