package com.example.testexoplayer1.screen.listmovie

import com.example.testexoplayer1.data.repository.MovieRepository
import com.example.testexoplayer1.data.repository.NetworkResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesPresenter(private val movieRepository: MovieRepository) : MovieContract.Presenter {
    private var mView: MovieContract.View? = null
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    override fun setView(view: MovieContract.View?) {
        this.mView = view
    }

    fun detachView() {
        mView = null
        job.cancel()
    }

    override fun getListMovie() {
        mView?.showLoading()

        scope.launch {
            val result = withContext(Dispatchers.IO) {
                movieRepository.fetchMovies()
            }
            mView?.hideLoading()
            when (result) {
                is NetworkResult.Error -> {
                    mView?.showError(result.message ?: "Error to fetch movies")
                }

                is NetworkResult.Success -> {
                    mView?.fetchMovies(result.data)
                }
            }
        }
    }
}