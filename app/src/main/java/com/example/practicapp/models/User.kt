package com.example.practicapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "user_table")
data class User(
    @SerializedName("name")
    var nameUser: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String ):Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}