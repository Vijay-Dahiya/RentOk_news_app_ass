package com.app.newsapp.model.remote

import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("name")
    val name: String,
)