package com.example.practicapp.models


import com.google.gson.annotations.SerializedName

data class LocationInfo(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)