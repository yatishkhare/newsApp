package com.example.newsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.API.ApiCallFactory

class ViewModelFactory constructor(private val apiCallFactory: ApiCallFactory): ViewModelProvider.Factory {



    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return if (modelClass.isAssignableFrom(MainViewModel::class.java!!)) {

            MainViewModel(MainRepository(apiCallFactory)) as T

        } else {

            throw IllegalArgumentException("ViewModel Not Found")

        }

    }

}