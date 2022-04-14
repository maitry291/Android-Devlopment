package com.example.project

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit

class ApiController {

    companion object{

        private const val url="http://10.53.141.154/govschemes/"

        val conn= OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS).build()

        val gson=GsonBuilder().create()

        var clientObject: ApiController? =null

        var retrofit: Retrofit =
            Retrofit.Builder().baseUrl(url).client(conn).addConverterFactory(GsonConverterFactory.create(
                gson)).build()

        //step2
        fun getInstance():ApiController{
            if(clientObject==null)
                return ApiController()
            return clientObject as ApiController
        }

        //step3
       fun createAPI():apiSet{
           return retrofit.create(apiSet::class.java)
       }

    }


}