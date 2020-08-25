package com.alejandro8a.androidTemplate.presentation.character

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.alejandro8a.androidTemplate.data.repository.CharacterCache
import com.alejandro8a.androidTemplate.network.model.CharacterResponse
import com.alejandro8a.androidTemplate.data.repository.CharacterMapper
import com.alejandro8a.androidTemplate.database.model.CharacterEntity
import com.alejandro8a.androidTemplate.domain.usecase.character.GetCharacterUseCase
import com.alejandro8a.androidTemplate.domain.usecase.base.UseCaseResponse
import com.alejandro8a.androidTemplate.domain.usecase.character.GetAllCharactersUseCase
import com.alejandro8a.androidTemplate.domain.usecase.character.SaveCharacterUseCase
import com.alejandro8a.androidTemplate.extensions.asLiveData
import com.alejandro8a.androidTemplate.network.ErrorModel
import com.alejandro8a.androidTemplate.presentation.base.BaseViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest

class CharacterViewModel constructor(
    private val getCharacterUseCase: GetCharacterUseCase,
    private val saveCharacterUseCase: SaveCharacterUseCase,
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val characterMapper: CharacterMapper,
    private val characterCache: CharacterCache
) : BaseViewModel() {

    private val TAG = CharacterViewModel::class.java.name

    private val _uiCharacter = MutableLiveData<CharacterProfile>()
    val uiCharacter = _uiCharacter.asLiveData()

    private val _uiAllCharacters = MutableLiveData<List<CharacterProfile>>()
    val uiAllCharacters = _uiAllCharacters.asLiveData()

    private val _showProgressBar = MutableLiveData<Boolean>()
    val showProgressBar = _showProgressBar.asLiveData()

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage = _errorMessage.asLiveData()

    fun getCharacter() {
        _showProgressBar.value = true
        getCharacterUseCase.invoke(scope, null, object : UseCaseResponse<List<CharacterResponse>> {
            override fun onSuccess(result: List<CharacterResponse>) {
                _showProgressBar.value = false
                characterCache.characterResponse = result[0]
                _uiCharacter.postValue(characterMapper.toUiProfile(result[0]))
            }

            override fun onError(errorModel: ErrorModel) {
                Log.e(TAG, errorModel.getErrorMessage())
                _showProgressBar.value = false
                _errorMessage.value = errorModel.message
            }
        })
    }

    fun saveCharacter() {
        saveCharacterUseCase.invoke(scope, characterCache.characterResponse.mapToRoomEntity(), object : UseCaseResponse<Unit> {
            override fun onSuccess(result: Unit) {
                //No-op
            }

            override fun onError(errorModel: ErrorModel) {
                _errorMessage.value = errorModel.message
            }
        })
    }

    @InternalCoroutinesApi
    fun getAllCharacters() {
        getAllCharactersUseCase.invoke(scope, null, object : UseCaseResponse<List<CharacterEntity>> {
            override fun onSuccess(result: List<CharacterEntity>) {
                _uiAllCharacters.postValue(characterMapper.toUiProfileList(result))
            }

            override fun onError(errorModel: ErrorModel) {
                _errorMessage.value = errorModel.message
            }

        })
    }
}