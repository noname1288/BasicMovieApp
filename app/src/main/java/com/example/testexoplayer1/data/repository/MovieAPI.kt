package com.example.testexoplayer1.data.repository

import com.example.testexoplayer1.data.repository.dto.GetMovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface MovieAPI {
    @GET("/danh-sach/phim-moi-cap-nhat-v3")
    suspend fun getAllMovies() : Response<GetMovieResponse>
}