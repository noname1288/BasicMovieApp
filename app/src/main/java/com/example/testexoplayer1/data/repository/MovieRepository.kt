package com.example.testexoplayer1.data.repository

import android.util.Log
import com.example.testexoplayer1.data.model.MovieModel

class MovieRepository(private val movieApi: MovieAPI) {
    private val TAG = "MovieRepository"

    suspend fun fetchMovies(): NetworkResult<List<MovieModel>> {
        val response = movieApi.getAllMovies()

        return if (response.isSuccessful) {
            val body = response.body()
            Log.d(TAG, "fetchMovies: Response body: $body")

            if (body != null && body.status) {
                NetworkResult.Success(body.items)
            } else {
                NetworkResult.Error(body?.msg ?: "Unknown error")
            }
        } else {
            Log.d(TAG, "fetchMovies: Response failed with code ${response.code()}")
            NetworkResult.Error("Network call failed with code: ${response.code()}")
        }
    }

    companion object {
        val instance: MovieRepository by lazy {
            MovieRepository(ApiClient.movieApi)
        }
    }
}
