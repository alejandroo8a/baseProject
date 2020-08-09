package com.alejandro8a.androidTemplate.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alejandro8a.androidTemplate.database.CHARACTER_TABLE_NAME
import com.alejandro8a.androidTemplate.database.model.CharacterEntity

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCharacter(character: CharacterEntity)

    @Query("SELECT * FROM $CHARACTER_TABLE_NAME")
    suspend fun getAllCharacters(): List<CharacterEntity>

}