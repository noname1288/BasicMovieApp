package com.example.testexoplayer1.data.repository.dto

import com.example.testexoplayer1.data.model.MovieWrapper

data class GetMovieResponse(
    val status: Boolean,
    val msg: String,
    val items: List<MovieWrapper>
)
