package com.alejandro8a.androidTemplate.domain.repository

import com.alejandro8a.androidTemplate.database.model.CharacterEntity
import com.alejandro8a.androidTemplate.network.model.CharacterResponse

interface CharacterRepository {

    suspend fun getCharacter(): List<CharacterResponse>

    suspend fun saveCharacter(characterEntity: CharacterEntity)
}