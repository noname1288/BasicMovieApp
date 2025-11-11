package com.example.testexoplayer1.data.model

import com.google.gson.annotations.SerializedName

data class MovieModel(
    @SerializedName("_id")
    val id: String = "",

    val actor: List<String> = emptyList(),
    val category: List<Category> = emptyList(),

    val chieurap: Boolean = false,
    val content: String = "",
    val country: List<Country> = emptyList(),
    val director: List<String> = emptyList(),

    @SerializedName("episode_current")
    val episodeCurrent: String = "",

    @SerializedName("episode_total")
    val episodeTotal: String = "",

    val lang: String = "",
    val name: String = "",

    @SerializedName("origin_name")
    val originName: String = "",

    @SerializedName("poster_url")
    val posterUrl: String = "",

    val quality: String = "",
    val slug: String = "",
    val status: String = "",

    @SerializedName("thumb_url")
    val thumbUrl: String = "",

    val time: String = "",

    @SerializedName("trailer_url")
    val trailerUrl: String = "",

    val type: String = "",
    val view: Int = 0,
    val year: Int = 0
)
