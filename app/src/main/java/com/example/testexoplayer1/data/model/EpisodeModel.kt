package com.example.testexoplayer1.data.model

import com.google.gson.annotations.SerializedName

data class EpisodeModel(
    val name: String = "",
    val slug: String = "",

    @SerializedName("filename")
    val filename: String = "",

    @SerializedName("link_embed")
    val linkEmbed: String = "",

    @SerializedName("link_m3u8")
    val linkM3u8: String = ""
)
