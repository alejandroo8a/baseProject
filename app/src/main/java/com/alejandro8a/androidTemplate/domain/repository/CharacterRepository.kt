package com.alejandro8a.androidTemplate.domain.repository

import com.alejandro8a.androidTemplate.network.model.CharacterResponse

interface CharacterRepository {

    suspend fun getCharacter(): List<CharacterResponse>
}