package com.example.data.remote

import com.example.data.remote.apiservices.FilmsApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

class RetrofitClient {

    private val okHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor())
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofitClient = Retrofit.Builder()
        .baseUrl("https://ghibliapi.vercel.app/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun filmsApiService() = retrofitClient.create(FilmsApiService::class.java)
}