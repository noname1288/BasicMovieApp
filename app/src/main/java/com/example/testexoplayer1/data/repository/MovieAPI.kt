package com.example.testexoplayer1.data.repository

import com.example.testexoplayer1.data.repository.dto.GetMovieResponse
import com.example.testexoplayer1.data.repository.dto.MovieDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieAPI {
    @GET("/danh-sach/phim-moi-cap-nhat-v3")
    suspend fun getAllMovies() : Response<GetMovieResponse>

    @GET("/phim/{slug}")
    suspend fun getMovieBySlug(
        @Path("slug") slug: String
    ) : Response<MovieDetailResponse>
}