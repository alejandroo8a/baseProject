package com.alejandro8a.androidTemplate.domain.usecase

import com.alejandro8a.androidTemplate.data.CharacterData
import com.alejandro8a.androidTemplate.domain.repository.CharacterRepository
import com.alejandro8a.androidTemplate.domain.usecase.base.UseCase
import com.alejandro8a.androidTemplate.network.ApiErrorHandle

class CharacterUseCase constructor(
    private val characterRepository: CharacterRepository,
    apiErrorHandle: ApiErrorHandle?
): UseCase<CharacterData, Any?>(apiErrorHandle){

    override suspend fun run(params: Any?): CharacterData {
        return characterRepository.getCharacter()
    }

}