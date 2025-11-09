package com.example.testexoplayer1.data.repository

object ApiClient {
    val movieApi: MovieAPI by lazy {
        RetrofitHelper.getInstance().create(MovieAPI::class.java)
    }
}
