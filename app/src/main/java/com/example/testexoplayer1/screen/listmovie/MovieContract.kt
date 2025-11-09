package com.example.testexoplayer1.screen.listmovie

import com.example.testexoplayer1.data.model.MovieModel
import com.example.testexoplayer1.utils.base.BasePresenter

interface MovieContract {
    interface View{
        fun showLoading()
        fun hideLoading()
        fun fetchMovies(data: List<MovieModel>)
        fun showError(msg: String)
    }

    interface Presenter : BasePresenter<View>{
        fun getListMovie()
    }
}
