package com.alejandro8a.androidTemplate.di

import com.alejandro8a.androidTemplate.data.repository.CharacterCache
import com.alejandro8a.androidTemplate.data.repository.CharacterMapper
import com.alejandro8a.androidTemplate.data.repository.CharacterRepositoryImpl
import com.alejandro8a.androidTemplate.database.dao.CharacterDao
import com.alejandro8a.androidTemplate.domain.repository.CharacterRepository
import com.alejandro8a.androidTemplate.network.ApiService
import com.alejandro8a.androidTemplate.network.model.CharacterResponse
import org.koin.dsl.module

val RepositoryModule = module {

    single { createCharacterRepository(get(), get()) }

}

fun createCharacterRepository(apiService: ApiService, characterDao: CharacterDao): CharacterRepository {
    return CharacterRepositoryImpl(apiService, characterDao)
}

fun createCharacterMapper(): CharacterMapper {
    return CharacterMapper()
}

fun createCharacterCache(): CharacterCache {
    return CharacterCache()
}