package com.alejandro8a.androidTemplate.presentation.character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.alejandro8a.androidTemplate.data.repository.CharacterCache
import com.alejandro8a.androidTemplate.data.repository.CharacterMapper
import com.alejandro8a.androidTemplate.domain.repository.CharacterRepository
import com.alejandro8a.androidTemplate.extensions.asLiveData
import com.alejandro8a.androidTemplate.network.ApiErrorHandle
import com.alejandro8a.androidTemplate.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CharacterViewModel constructor(
    private val characterRepository: CharacterRepository,
    private val characterMapper: CharacterMapper,
    private val characterCache: CharacterCache
) : BaseViewModel() {

    private val _uiCharacter = MutableLiveData<CharacterProfile>()
    val uiCharacter = _uiCharacter.asLiveData()

    private val _uiAllCharacters = characterRepository
        .getAllCharacters()
        .map {
            characterMapper.toUiProfileList(it)
        }
        .asLiveData(scope.coroutineContext)
    val uiAllCharacters = _uiAllCharacters

    private val _showProgressBar = MutableLiveData<Boolean>()
    val showProgressBar = _showProgressBar.asLiveData()

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage = _errorMessage.asLiveData()

    fun getCharacter() {
        _showProgressBar.value = true
        scope.launch {
            characterRepository
                .getCharacter()
                .onStart {
                    _showProgressBar.value = true
                }
                .map {
                    characterCache.characterResponse = it[0]
                    characterMapper.toUiProfile(it[0])
                }
                .catch {
                    _showProgressBar.value = false
                    _errorMessage.value = ApiErrorHandle.traceErrorException(it).getErrorMessage()
                }
                .collect {
                    _showProgressBar.value = false
                    _uiCharacter.postValue(it)
                }
        }
    }

    fun saveCharacter() {
        scope.launch {
            characterRepository
                .saveCharacter(characterCache.characterResponse.mapToRoomEntity())
        }
    }
}