package com.example.newsapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.API.ApiArticleService
import com.example.newsapp.API.ApiCallFactory
import com.example.newsapp.API.ApiService
import com.example.newsapp.API.RetrofitBuilder
import com.example.newsapp.Adapters.MainAdapter
import com.example.newsapp.databinding.FragmentFirstBinding
import com.example.newsapp.enum.Status
import com.example.newsapp.viewmodel.ViewModelFactory
import com.example.newsapp.viewmodel.MainRepository
import com.example.newsapp.viewmodel.MainViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding?=null
    private lateinit var viewModel: MainViewModel
    private val retrofitService = ApiService.getInstance()
    private val apiRetrofitService = ApiArticleService.getInstance()
    private val adapter = MainAdapter()

    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding?.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(

            owner = this,

            factory = ViewModelFactory(ApiCallFactory(RetrofitBuilder.apiService, RetrofitBuilder.apiArticleService))

        )[MainViewModel::class.java]
        val recyclerView = binding?.recyclerview

        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(context)

        viewModel.articleList.observe(viewLifecycleOwner, Observer {
            Log.d("check","onViewCreated: $it")
            adapter.setNewsArticle(it)
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
        })
        viewModel.getNews().observe(viewLifecycleOwner) {

            it?.let { resource ->

                when (resource.status) {

                    Status.SUCCESS -> {

                        recyclerView?.visibility = View.VISIBLE

                        resource.data?.let {
                            adapter.setNewsArticle(
                                it.articles ?: emptyList()
                            )
                        }

                    }

                    Status.ERROR -> {

                        recyclerView?.visibility = View.VISIBLE

                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()

                    }

                    Status.LOADING -> {

                        recyclerView?.visibility = View.GONE

                    }

                }

            }

        }
        //viewModel.getArticles()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}