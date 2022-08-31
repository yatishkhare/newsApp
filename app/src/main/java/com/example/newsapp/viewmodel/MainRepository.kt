package com.example.newsapp.viewmodel

import com.example.newsapp.API.ApiArticleService
import com.example.newsapp.API.ApiCallFactory
import com.example.newsapp.API.ApiService
import com.example.newsapp.Data.NewsArticles
import com.example.newsapp.Data.NewsData

class MainRepository (private val apiCallFactory: ApiCallFactory) {
        suspend fun getNews() : NewsData{
            return apiCallFactory.getNews()
        }
        suspend fun getArticles(): List<NewsArticles>{
            return apiCallFactory.getArticles()
        }
    }
