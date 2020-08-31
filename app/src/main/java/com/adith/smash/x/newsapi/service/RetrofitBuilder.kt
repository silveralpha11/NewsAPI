package com.adith.smash.x.newsapi.service

import com.adith.smash.x.newsapi.model.ResponseNews
import com.google.gson.internal.`$Gson$Preconditions`
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers


object RetrofitBuilder {
    private val client = OkHttpClient.Builder().build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun getService() = retrofit.create(TopHeadlines::class.java)
}

interface TopHeadlines{
    @Headers("Authorization: d4203a2fb47d4227bdb163c71805d6d6")
    @GET ("/v2/top-headlines?country=id")
    fun fetchHeadlines(): Call<ResponseNews>
}