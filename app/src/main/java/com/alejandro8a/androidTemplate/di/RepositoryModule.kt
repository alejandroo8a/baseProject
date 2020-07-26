package com.alejandro8a.androidTemplate.di

import com.alejandro8a.androidTemplate.data.repository.CharacterMapper
import com.alejandro8a.androidTemplate.data.repository.CharacterRepositoryImpl
import com.alejandro8a.androidTemplate.domain.repository.CharacterRepository
import com.alejandro8a.androidTemplate.network.ApiService
import org.koin.dsl.module

val RepositoryModule = module {

    single { createCharacterRepository(get()) }

}

fun createCharacterRepository(apiService: ApiService): CharacterRepository {
    return CharacterRepositoryImpl(apiService)
}

fun createCharacterMapper(): CharacterMapper {
    return CharacterMapper()
}