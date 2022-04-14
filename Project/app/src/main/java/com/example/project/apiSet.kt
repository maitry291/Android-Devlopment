package com.example.project

import com.example.project.models.SchemesTable
import retrofit2.Call
import retrofit2.http.GET

interface apiSet {

    @GET("sqlconnection.php")
    fun getData():Call<List<SchemesTable>>  //will return the list which contains all rows of table as obj

}