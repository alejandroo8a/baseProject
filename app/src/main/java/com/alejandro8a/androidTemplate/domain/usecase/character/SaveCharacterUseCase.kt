package com.alejandro8a.androidTemplate.domain.usecase.character

import com.alejandro8a.androidTemplate.database.model.CharacterEntity
import com.alejandro8a.androidTemplate.domain.repository.CharacterRepository
import com.alejandro8a.androidTemplate.domain.usecase.base.UseCase
import com.alejandro8a.androidTemplate.network.ApiErrorHandle

class SaveCharacterUseCase constructor(
    private val characterRepository: CharacterRepository,
    apiErrorHandle: ApiErrorHandle
): UseCase<Unit, Any?>(apiErrorHandle){

    override suspend fun run(params: Any?) {
        characterRepository.saveCharacter(params as CharacterEntity)
    }

}