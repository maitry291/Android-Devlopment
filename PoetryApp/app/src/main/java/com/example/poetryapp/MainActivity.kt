package com.example.poetryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var recycler:RecyclerView
     private lateinit var retrofit: Retrofit
     private lateinit var apiInterface:ApiInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialization()
        getData()

    }
    private fun getData(){
        apiInterface.readPoetry().enqueue(object : Callback<GetPoetryResponse> {
            override fun onResponse(
                call: Call<GetPoetryResponse>,
                response: Response<GetPoetryResponse>
            ) {
                try{
                    if (response.body()?.status.equals("1")){
                        setAdapter(response.body()!!.list)
                    }else{
                        Toast.makeText(this@MainActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                    }
                }catch (e:Exception){
                    Toast.makeText(this@MainActivity, e.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<GetPoetryResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity,"failed : "+ t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun initialization(){
        recycler=findViewById<RecyclerView>(R.id.poetry_recycler)
        retrofit= RetrofitAPI().getClient()!!
        apiInterface=retrofit.create(ApiInterface::class.java)
    }
    fun setAdapter(list: ArrayList<PoetryModel>){
        val adapter:PoetryAdapter= PoetryAdapter(this,list)
        recycler.layoutManager=LinearLayoutManager(this)
        recycler.adapter=adapter
    }

}