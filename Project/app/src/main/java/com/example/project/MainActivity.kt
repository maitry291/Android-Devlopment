package com.example.project

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.Login
import com.example.project.R
import com.example.project.adapters.Adapter
import com.example.project.adapters.SchemesAdapter
import com.example.project.models.SchemesTable
import com.example.project.models.UserDetails
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection
import java.net.InetSocketAddress
import java.net.Socket
import java.util.logging.SocketHandler

class MainActivity : AppCompatActivity() {

    lateinit var recycler:RecyclerView
    lateinit var sa:SchemesAdapter
    val db=Firebase.database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        // get name from firebase
        /*db.getReference("Users").child(FirebaseAuth.getInstance().uid.toString()).addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                //for(snap in snapshot.children){
                    val model=snapshot.getValue(UserDetails::class.java)
                    //if(FirebaseAuth.getInstance().currentUser!!.uid.toString()==snap.key){
                        toolbar.title = "Hello, " + model!!.name + "😁"
                   // }
                //}
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })*/

        setSupportActionBar(toolbar as Toolbar)
        toolbar.title="Hello,"+FirebaseAuth.getInstance().currentUser?.uid+"👋"

        //some default schemes
        val default=ArrayList<SchemesTable>()


        //recycler initialize
        recycler=findViewById(R.id.recyclerView)
        recycler.layoutManager=LinearLayoutManager(this)
        recycler.adapter=SchemesAdapter(default,this)

        records.setOnClickListener {
            val dept : String=spinner_category.selectedItem.toString()
            val spinGender:String =spinner_gender.selectedItem.toString()
            val spinIncome:String=spinner_salary.selectedItem.toString()
            //val spinlage:Int=spinner_age.selectedItem.toString().subSequence(0,2)
            val spinDis:String=spinner_disability.selectedItem.toString()
            val spinCaste:String=spinner_caste.selectedItem.toString()

            val apiSet=ApiController.getClient().create(apiSet::class.java)
            var call : Call<List<SchemesTable>> = apiSet.getData()

            call.enqueue(object : Callback<List<SchemesTable>>{
                override fun onResponse(
                    call: Call<List<SchemesTable>>,
                    response: Response<List<SchemesTable>>
                ) {
                    //this list has the all data from table acc to query written in php file
                    val data: List<SchemesTable>? = response.body()

                    //to filer schemes from list we will apply loop here and store the filtered query in list
                    //tht list will link to recycler and showed to user

                    for(i in data!!){
                        val query:SchemesTable=i
                        if(i.Department.equals(dept) && i.gender.equals(spinGender) && i.disabilities.equals(spinDis) && i.caste.equals(spinCaste)) {
                            sa = SchemesAdapter(data as ArrayList<SchemesTable>, this@MainActivity)
                            recycler.layoutManager = LinearLayoutManager(this@MainActivity)
                            recycler.adapter = sa
                        }
                    }

                }

                override fun onFailure(call: Call<List<SchemesTable>>, t: Throwable) {
                    Log.d("error",t.toString())
                    Toast.makeText(this@MainActivity, "Failed $t", Toast.LENGTH_LONG).show()
                }

            })
        }


        val list1 = arrayListOf<String>(
            "Department",
            "Department of Education",
            "Department of Health",
            "Department of Emp. Women",
            "Department of Agriculture",
            "Department of Electricity",
            "Department of Housing",
            "Department of Senior citizen",
            "Department of Senior Employment"
        )
        val adapter1 = Adapter(list1, this)
        findViewById<Spinner>(R.id.spinner_category).adapter = adapter1

        val list2 = arrayListOf<String>("Gender","Male", "Female", "Other", "View for all")
        val adapter2 = Adapter(list2, this)
        findViewById<Spinner>(R.id.spinner_gender).adapter = adapter2

        val list3 = arrayListOf<String>("Income","< 50,000", "50,000 - 1,00,000", "1,00,000 - 5,00,000", "5,00,000 - 10,00,000","> 10,00,000","Rather not say")
        val adapter3 = Adapter(list3, this)
        findViewById<Spinner>(R.id.spinner_salary).adapter = adapter3

        val list4 = arrayListOf<String>("Age","<18","18-30","30-45","45-60",">60")
        val adapter4 = Adapter(list4, this)
        findViewById<Spinner>(R.id.spinner_age).adapter = adapter4

        val list5 = arrayListOf<String>("Disability","Yes","No")
        val adapter5 = Adapter(list5, this)
        findViewById<Spinner>(R.id.spinner_disability).adapter = adapter5

        val list6 = arrayListOf<String>("Caste","General","OBC","EWS","SC","ST")
        val adapter6 = Adapter(list6, this)
        findViewById<Spinner>(R.id.spinner_caste).adapter = adapter6

    }

    // Inflating menu to Toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // Code to performed when menuItem clicked
            R.id.i2 ->{
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this, Login::class.java))
                overridePendingTransition(
                    androidx.appcompat.R.anim.abc_fade_in,
                    androidx.appcompat.R.anim.abc_fade_out
                )
            }
        }
        return true
    }
}