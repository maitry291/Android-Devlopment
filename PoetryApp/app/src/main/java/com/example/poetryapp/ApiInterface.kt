package com.example.poetryapp

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("readPoetry.php")
    fun readPoetry(): Call<GetPoetryResponse>

}