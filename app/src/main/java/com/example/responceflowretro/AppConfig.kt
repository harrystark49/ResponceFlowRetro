package com.example.responceflowretro

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppConfig {
    var BASE_URL="https://jsonplaceholder.typicode.com/users/"
    var interceptor=HttpLoggingInterceptor().apply {
        level=HttpLoggingInterceptor.Level.BODY
    }

    var client=OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addInterceptor(OaAuthInterceptor())
        .connectTimeout(2,TimeUnit.MINUTES)
        .build()
    @Provides
    @Singleton
    @Named("retro")
    fun ApiService():retroInterface{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(retroInterface::class.java)
    }
}