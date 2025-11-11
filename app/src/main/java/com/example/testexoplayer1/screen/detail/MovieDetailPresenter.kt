package com.example.testexoplayer1.screen.detail

import com.example.testexoplayer1.data.repository.MovieRepository
import com.example.testexoplayer1.data.repository.NetworkResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailPresenter(private val movieRepository: MovieRepository) :
    MovieDetailContract.Presenter {
    private var mView: MovieDetailContract.View? = null
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    override fun setView(view: MovieDetailContract.View?) {
        this.mView = view
    }

    override fun detachView() {
        mView = null
        job.cancel()
    }

    override fun fetchDataBySlug(slug: String) {
        mView?.showLoading()

        scope.launch {
            val result = withContext(Dispatchers.IO){
                movieRepository.getMovieBySlug(slug)
            }

            mView?.hideLoading()
            when(result){
                is NetworkResult.Error -> mView?.showError(result.message ?: "Can't fetch movie $slug")
                is NetworkResult.Success -> {
                    val movieDetail = result.data.first
                    val episodeWrapper = result.data.second

                    mView?.showMovieDetail(movieDetail, episodeWrapper)
                }
            }
        }
    }
}
