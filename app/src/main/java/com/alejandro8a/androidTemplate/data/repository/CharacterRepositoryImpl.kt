package com.alejandro8a.androidTemplate.data.repository

import com.alejandro8a.androidTemplate.database.dao.CharacterDao
import com.alejandro8a.androidTemplate.database.model.CharacterEntity
import com.alejandro8a.androidTemplate.network.model.CharacterResponse
import com.alejandro8a.androidTemplate.domain.repository.CharacterRepository
import com.alejandro8a.androidTemplate.network.ApiService
import kotlinx.coroutines.flow.Flow

class CharacterRepositoryImpl(
    private val apiService: ApiService,
    private val characterDao: CharacterDao
) : CharacterRepository {

    override suspend fun getCharacter(): List<CharacterResponse> {
        return apiService.getCharacter()
    }

    override suspend fun saveCharacter(characterEntity: CharacterEntity) {
        characterDao.saveCharacter(characterEntity)
    }

    override suspend fun getAllCharacters(): Flow<List<CharacterEntity>>{
        return characterDao.getAllCharacters()
    }

}