package com.example.practicapp.models


import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<Result>
)