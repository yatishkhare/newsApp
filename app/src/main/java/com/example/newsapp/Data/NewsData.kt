package com.example.newsapp.Data

data class NewsData (

    val status: String,
    val totalResults: Int,
    val articles: List<NewsArticles>
)
