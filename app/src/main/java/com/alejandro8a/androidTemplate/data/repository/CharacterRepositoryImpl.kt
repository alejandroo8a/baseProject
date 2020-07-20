package com.alejandro8a.androidTemplate.data.repository

import com.alejandro8a.androidTemplate.data.CharacterData
import com.alejandro8a.androidTemplate.domain.repository.CharacterRepository
import com.alejandro8a.androidTemplate.network.ApiService

class CharacterRepositoryImpl(private val apiService: ApiService): CharacterRepository {

    override suspend fun getCharacter(): CharacterData {
        return apiService.getCharacter()
    }

}