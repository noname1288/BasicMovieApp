package com.example.testexoplayer1.screen.watch

import com.example.testexoplayer1.utils.base.BasePresenter

interface WatchMovieContract {
    interface View{
        fun showLoading()
        fun hideLoading()
        fun showError(message: String)
    }

    interface Presenter: BasePresenter<View>{
        fun detachView()
    }
}
