package com.example.testexoplayer1.data.repository.dto

import com.example.testexoplayer1.data.model.EpisodeWrapper
import com.example.testexoplayer1.data.model.MovieModel
import com.example.testexoplayer1.data.model.MovieWrapper

data class MovieDetailResponse(
    val status: Boolean,
    val msg: String,
    val movie: MovieModel,
    val episodes: List<EpisodeWrapper>
)
