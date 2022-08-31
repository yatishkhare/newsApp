package com.example.newsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.newsapp.Data.NewsArticles
import com.example.newsapp.Data.NewsData
import com.example.newsapp.Data.Resource
import kotlinx.coroutines.Dispatchers
/*import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response*/

class MainViewModel constructor(private val repository: MainRepository)  : ViewModel() {

        //val newsList = MutableLiveData<List<NewsData>>()
        val errorMessage = MutableLiveData<String>()
        val articleList = MutableLiveData<List<NewsArticles>>()

        fun getNews() = liveData(Dispatchers.IO) {

            emit(Resource.loading(data = null))

            try {

                emit(Resource.success(data = repository.getNews()))

            } catch (exception: Exception) {

                emit(Resource.error(data = null, msg = exception.message ?: "Error Occurred!"))

            }

        }

        /*fun getArticles(){
            val response = repository.getArticles()
            response.enqueue(object : Callback<List<NewsArticles>>{
                override fun onResponse(
                    call: Call<List<NewsArticles>>,
                    response: Response<List<NewsArticles>>
                ) {
                    articleList.postValue(response.body())
                }

                override fun onFailure(call: Call<List<NewsArticles>>, t: Throwable) {
                    errorMessage.postValue(t.message)
                }
            })
        }*/
    }
