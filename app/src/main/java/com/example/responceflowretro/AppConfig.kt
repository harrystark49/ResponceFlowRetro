package com.example.responceflowretro

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppConfig {
    var BASE_URL="https://jsonplaceholder.typicode.com/users/"

    fun ApiService():retroInterface{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(retroInterface::class.java)
    }
}