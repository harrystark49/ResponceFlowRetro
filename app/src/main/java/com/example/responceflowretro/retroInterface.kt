package com.example.responceflowretro

import retrofit2.http.GET
import retrofit2.http.Path

interface retroInterface {

    @GET("{id}")
    suspend fun getData(@Path("id")id:Int):res
}