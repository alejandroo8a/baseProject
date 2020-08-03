package com.alejandro8a.androidTemplate.domain.usecase

import com.alejandro8a.androidTemplate.network.model.CharacterResponse
import com.alejandro8a.androidTemplate.domain.repository.CharacterRepository
import com.alejandro8a.androidTemplate.domain.usecase.base.UseCase
import com.alejandro8a.androidTemplate.network.ApiErrorHandle

class CharacterUseCase constructor(
    private val characterRepository: CharacterRepository,
    apiErrorHandle: ApiErrorHandle
): UseCase<List<CharacterResponse>, Any?>(apiErrorHandle){

    override suspend fun run(params: Any?): List<CharacterResponse> {
        return characterRepository.getCharacter()
    }

}