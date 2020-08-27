package com.alejandro8a.androidTemplate.domain.repository

import com.alejandro8a.androidTemplate.database.model.CharacterEntity
import com.alejandro8a.androidTemplate.network.model.CharacterResponse
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharacter(): Flow<List<CharacterResponse>>

    suspend fun saveCharacter(characterEntity: CharacterEntity)

    fun getAllCharacters(): Flow<List<CharacterEntity>>
}