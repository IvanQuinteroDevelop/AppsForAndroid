package com.example.practicapp.models


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.practicapp.converters.ConverterEpisode
import com.google.gson.annotations.SerializedName

@Entity(tableName = "results_table")
data class Result(
    @SerializedName("name")
    val name: String,
    @SerializedName("created")
    val created: String,
    @SerializedName("episode")
    @TypeConverters(ConverterEpisode::class)
    val episode: List<String>?,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("location")
    @Embedded(prefix = "location_")
    val location: LocationInfo?,
    @SerializedName("origin")
    @Embedded(prefix = "origin_")
    var origin: LocationInfo?,
    @SerializedName("species")
    val species: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
)