package com.alejandro8a.androidTemplate.domain.usecase.character

import com.alejandro8a.androidTemplate.network.model.CharacterResponse
import com.alejandro8a.androidTemplate.domain.repository.CharacterRepository
import com.alejandro8a.androidTemplate.domain.usecase.base.UseCase
import com.alejandro8a.androidTemplate.network.ApiErrorHandle
import kotlinx.coroutines.flow.Flow

class GetCharacterUseCase constructor(
    private val characterRepository: CharacterRepository,
    apiErrorHandle: ApiErrorHandle
): UseCase<Flow<List<CharacterResponse>>, Any?>(apiErrorHandle){

    override suspend fun run(params: Any?): Flow<List<CharacterResponse>> {
        return characterRepository.getCharacter()
    }

}