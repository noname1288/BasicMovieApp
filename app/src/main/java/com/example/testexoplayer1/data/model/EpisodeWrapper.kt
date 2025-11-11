package com.example.testexoplayer1.data.model

import com.google.gson.annotations.SerializedName

data class EpisodeWrapper(
    @SerializedName("server_name")
    val serverName: String = "",
    @SerializedName("server_data")
    val serverData: List<EpisodeModel> = emptyList()
)
