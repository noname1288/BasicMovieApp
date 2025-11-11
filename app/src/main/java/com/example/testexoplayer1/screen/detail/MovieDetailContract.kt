package com.example.testexoplayer1.screen.detail

import com.example.testexoplayer1.data.model.EpisodeWrapper
import com.example.testexoplayer1.data.model.MovieModel
import com.example.testexoplayer1.data.model.MovieWrapper
import com.example.testexoplayer1.utils.base.BasePresenter

interface MovieDetailContract {
    interface View{
        fun showLoading()
        fun hideLoading()
        fun showError(message: String)
        fun showMovieDetail(movieDetail: MovieModel, episodeWrapper: List<EpisodeWrapper>)
    }

    interface Presenter : BasePresenter<View>{
        fun fetchDataBySlug(slug: String)
        fun detachView()
    }
}