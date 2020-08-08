package com.alejandro8a.androidTemplate.di

import com.alejandro8a.androidTemplate.domain.repository.CharacterRepository
import com.alejandro8a.androidTemplate.domain.usecase.character.GetCharacterUseCase
import com.alejandro8a.androidTemplate.network.ApiErrorHandle
import org.koin.dsl.module

val UseCaseModule = module {

    single { createCharacterUseCase(createCharacterRepository(get()), createApiErrorHandle()) }

}

fun createCharacterUseCase(
    characterRepository: CharacterRepository,
    apiErrorHandle: ApiErrorHandle
): GetCharacterUseCase {
    return GetCharacterUseCase(
        characterRepository,
        apiErrorHandle
    )
}