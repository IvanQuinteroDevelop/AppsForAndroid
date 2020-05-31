package com.example.practicapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.practicapp.models.Result

@Dao
interface Dao {

    @Insert(onConflict = REPLACE)
    fun insertCharacters(characters: List<Result>)

    @Query("SELECT * FROM results_table")
    fun getCharactersFromDB():List<Result>

    @Query("DELETE FROM results_table")
    fun deleteCharacters()

    @Query("SELECT * FROM results_table r WHERE r.name LIKE '%' || :name || '%'")
    fun searchCharacter(name: String):List<Result>
}