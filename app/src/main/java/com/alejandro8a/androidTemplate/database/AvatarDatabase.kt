package com.alejandro8a.androidTemplate.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alejandro8a.androidTemplate.database.dao.CharacterDao
import com.alejandro8a.androidTemplate.database.model.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1, exportSchema = false)
abstract class AvatarDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}