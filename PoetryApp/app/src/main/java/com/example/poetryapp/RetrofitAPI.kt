package com.example.poetryapp

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitAPI {
    private var retrofit: Retrofit? =null

    fun getClient():Retrofit?{
        if(retrofit==null) {
            val okHttpClient = OkHttpClient.Builder().build()

            val gson = GsonBuilder().create()

            retrofit = Retrofit.Builder().baseUrl("https://192.168.232.198/poetryApis/")
                .client(okHttpClient).addConverterFactory(GsonConverterFactory.create(gson)).build()
        }
        return retrofit
    }

}