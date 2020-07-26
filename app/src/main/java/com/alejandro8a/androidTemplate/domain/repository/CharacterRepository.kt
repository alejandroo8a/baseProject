package com.alejandro8a.androidTemplate.domain.repository

import com.alejandro8a.androidTemplate.data.CharacterData

interface CharacterRepository {

    suspend fun getCharacter(): List<CharacterData>
}