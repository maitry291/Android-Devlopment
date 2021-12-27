package com.example.permissions

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    private val CALL_PERMISSSION_CODE:Int=10  //we can give any integer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val phoneNum:String=e.text.toString()

            //we have to create intent that performs action of call
            val intent=Intent(Intent.ACTION_CALL)
            //we have to pass phone  num as uri string
            intent.setData(Uri.parse("tel:$phoneNum"))  //why "tel:" is needed?
            //makes call if permission is already granted
            if (makeCall())
              startActivity(intent)
            //if permission is not already granted then we have to take permission
            else
                makeCallAfterPermission()
        }
        
    }
    fun makeCall():Boolean{
        return checkPermission(android.Manifest.permission.CALL_PHONE,CALL_PERMISSSION_CODE)
    }
    fun makeCallAfterPermission(){
        /*If your app does not have the requested permissions the user will be presented with UI
        for accepting them. After the user has accepted or rejected the requested permissions
        you will receive a callback reporting whether the permissions were granted or not.
       Your activity has to implement ActivityCompat.OnRequestPermissionsResultCallback and
       the results of permission requests will be delivered to its
     ActivityCompat.OnRequestPermissionsResultCallback.onRequestPermissionsResult(int, String[], int[]) method.
       */
        //requestPermission call invokes call for onRequestPermissionsResult
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE),CALL_PERMISSSION_CODE)
        makeCall()
    }
    // Function to check permission is granted or not
   fun checkPermission(permission:String,requestCode:Int):Boolean {
        if (ContextCompat.checkSelfPermission(
                this,
                permission
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
           return false
        }
        else{
            Toast.makeText(this, "Already Granted", Toast.LENGTH_SHORT).show()
            return true
        }
    }
   //this method is invoked when requestPermissions() is called.
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode==CALL_PERMISSSION_CODE){
            // Checking whether user granted the permission or not.
            if(grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}