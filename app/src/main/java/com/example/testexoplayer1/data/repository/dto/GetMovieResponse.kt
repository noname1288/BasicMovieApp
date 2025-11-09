package com.example.testexoplayer1.data.repository.dto

import com.example.testexoplayer1.data.model.MovieModel

data class GetMovieResponse(
    val status: Boolean,
    val msg: String,
    val items: List<MovieModel>
)
