package com.example.newsapp.API

import com.example.newsapp.Data.NewsArticles
import com.example.newsapp.Data.NewsData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("top-headlines?sources=google-news-in&apiKey=91cbd0e9aad344d984794abcd4c13255")
    suspend fun getNews() : NewsData


    companion object {
        var retrofitService: ApiService? =null

        fun getInstance() : ApiService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://newsapi.org/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(ApiService::class.java)
            }
            return retrofitService!!
        }
    }
}