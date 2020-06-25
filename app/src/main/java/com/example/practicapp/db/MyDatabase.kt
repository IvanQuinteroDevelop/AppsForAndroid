package com.example.practicapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.practicapp.converters.ConverterEpisode
import com.example.practicapp.models.Result

@Database(entities = [Result::class], version = 8, exportSchema = false)
@TypeConverters(value = [ConverterEpisode::class])
abstract class MyDatabase : RoomDatabase() {
    abstract fun dao(): Dao
}