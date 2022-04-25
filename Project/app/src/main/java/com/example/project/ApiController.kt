package com.example.project

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit

class ApiController {

    companion object{

        private const val url="https://well-coupled-fruits.000webhostapp.com/test/"

        val conn= OkHttpClient.Builder()
                  .build()

        val gson=GsonBuilder().setLenient().create()
        var retrofit: Retrofit? =null

        fun getClient():Retrofit{
            if(retrofit==null)
            {
                retrofit = Retrofit.Builder().baseUrl(url).client(conn).addConverterFactory(
                    GsonConverterFactory.create(gson)
                ).build()
                return retrofit as Retrofit
            }
            return retrofit as Retrofit
        }

    }


}