package com.example.practicapp.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class User(
    @SerializedName("name")
    var name: String = "",
    @SerializedName("email")
    var email: String = ""):Serializable {
}