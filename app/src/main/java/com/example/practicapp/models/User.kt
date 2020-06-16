package com.example.practicapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_table")
data class User(
    @SerializedName("id") @PrimaryKey(autoGenerate = true) var id: Int,
    @SerializedName("name")
    var nameUser: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String ) {
}