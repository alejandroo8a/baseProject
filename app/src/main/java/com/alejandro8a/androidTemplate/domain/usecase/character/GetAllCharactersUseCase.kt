package com.alejandro8a.androidTemplate.domain.usecase.character

import com.alejandro8a.androidTemplate.database.model.CharacterEntity
import com.alejandro8a.androidTemplate.domain.repository.CharacterRepository
import com.alejandro8a.androidTemplate.domain.usecase.base.UseCase
import com.alejandro8a.androidTemplate.network.ApiErrorHandle
import com.alejandro8a.androidTemplate.network.model.CharacterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class GetAllCharactersUseCase constructor(
    private val characterRepository: CharacterRepository,
    apiErrorHandle: ApiErrorHandle
): UseCase<List<CharacterEntity>, Any?>(apiErrorHandle){

    override suspend fun run(params: Any?): List<CharacterEntity> {
        return characterRepository.getAllCharacters()
            .flowOn(Dispatchers.Default)
            .first()
}

}