package com.example.newsapp.API

class ApiCallFactory(private val apiService: ApiService, private val apiArticleService: ApiArticleService) {
    suspend fun getNews() = apiService.getNews()
    suspend fun getArticles() = apiArticleService.getArticles()
}

