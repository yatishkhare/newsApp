package com.example.newsapp.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.Data.NewsArticles
import com.example.newsapp.databinding.NewsItemBinding

class MainAdapter: RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    var news = mutableListOf<NewsArticles>()
    class MainViewHolder(private val binding: NewsItemBinding ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(news: NewsArticles){
            binding.tvTitle.text = news.title
            binding.author.text = news.author
            binding.time.text = "Published on -${news.publishedAt}"
            Glide.with(itemView.context).load(news.urlToImage).into(binding.imageView)
        }
    }
        @SuppressLint("NotifyDataSetChanged")
        fun setNewsArticle(newsArticle: List<NewsArticles>) {
            this.news = newsArticle.toMutableList()
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = NewsItemBinding.inflate(inflater, parent, false)
            return MainViewHolder(binding)
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            holder.bind(news[position])
        }

        override fun getItemCount(): Int {
            return news.size
        }
    }
