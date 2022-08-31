package com.example.newsapp.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitBuilder {



    private const val BASE_URL = "https://newsapi.org/v2/"



    private fun getRetrofit(): Retrofit {

        return Retrofit.Builder()

            .baseUrl(BASE_URL)

            .addConverterFactory(GsonConverterFactory.create()) // Serialization is a mechanism of converting the state of an object into a byte stream.

            .build() //Doesn't require the adapter

    }



    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
    val apiArticleService: ApiArticleService = getRetrofit().create(ApiArticleService::class.java)

}