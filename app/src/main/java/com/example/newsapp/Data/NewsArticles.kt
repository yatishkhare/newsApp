package com.example.newsapp.Data

data class NewsArticles (
    var title: String,
    var description: String,
    var publishedAt: String,
    var author: String? ,
    var urlToImage: String,
    var url: String
        )