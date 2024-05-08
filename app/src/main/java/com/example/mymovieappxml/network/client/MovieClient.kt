package com.example.mymovieappxml.network.client

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//TODO(BuildConfig)
object MovieClient {

    val intercepter = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(intercepter)
        .authenticator(Authentication())
        .build()

    // Builder
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    fun createMoviesService() = retrofit.create(MovieService::class.java)

}