package com.alejandro8a.androidTemplate.di

import com.alejandro8a.androidTemplate.domain.repository.CharacterRepository
import com.alejandro8a.androidTemplate.domain.usecase.character.GetCharacterUseCase
import com.alejandro8a.androidTemplate.domain.usecase.character.SaveCharacterUseCase
import com.alejandro8a.androidTemplate.network.ApiErrorHandle
import org.koin.dsl.module

val UseCaseModule = module {

    single { createGetCharacterUseCase(createCharacterRepository(get(), get()), createApiErrorHandle()) }

}

fun createGetCharacterUseCase(
    characterRepository: CharacterRepository,
    apiErrorHandle: ApiErrorHandle
): GetCharacterUseCase {
    return GetCharacterUseCase(
        characterRepository,
        apiErrorHandle
    )
}

fun createSaveCharacterUseCase(
    characterRepository: CharacterRepository,
    apiErrorHandle: ApiErrorHandle
): SaveCharacterUseCase {
    return SaveCharacterUseCase(
        characterRepository,
        apiErrorHandle
    )
}