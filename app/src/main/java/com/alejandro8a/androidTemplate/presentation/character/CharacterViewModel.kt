package com.alejandro8a.androidTemplate.presentation.character

import androidx.lifecycle.MutableLiveData
import com.alejandro8a.androidTemplate.data.repository.CharacterCache
import com.alejandro8a.androidTemplate.data.repository.CharacterMapper
import com.alejandro8a.androidTemplate.database.model.CharacterEntity
import com.alejandro8a.androidTemplate.domain.repository.CharacterRepository
import com.alejandro8a.androidTemplate.domain.usecase.base.UseCaseResponse
import com.alejandro8a.androidTemplate.domain.usecase.character.GetAllCharactersUseCase
import com.alejandro8a.androidTemplate.domain.usecase.character.SaveCharacterUseCase
import com.alejandro8a.androidTemplate.extensions.asLiveData
import com.alejandro8a.androidTemplate.network.ApiErrorHandle
import com.alejandro8a.androidTemplate.network.ErrorModel
import com.alejandro8a.androidTemplate.presentation.base.BaseViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CharacterViewModel constructor(
    private val characterRepository: CharacterRepository,
    private val saveCharacterUseCase: SaveCharacterUseCase,
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val characterMapper: CharacterMapper,
    private val characterCache: CharacterCache
) : BaseViewModel() {

    private val TAG = CharacterViewModel::class.java.name

    private val _uiCharacter = MutableLiveData<CharacterProfile>()
    //This is for one shot
//    private val _uiCharacter = characterRepository
//        .getCharacter()
//        .onStart {
//            _showProgressBar.value = true
//        }
//        .map {
//            characterMapper.toUiProfile(it[0])
//        }
//        .catch {
//            _showProgressBar.value = false
//            //_errorMessage.value = errorModel.message
//        }
//        .asLiveData(scope.coroutineContext)
    //val uiCharacter = _uiCharacter
    val uiCharacter = _uiCharacter.asLiveData()

    private val _uiAllCharacters = MutableLiveData<List<CharacterProfile>>()
    val uiAllCharacters = _uiAllCharacters.asLiveData()

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
        getAllCharactersUseCase.invoke(scope, null, object : UseCaseResponse<Flow<List<CharacterEntity>>> {
            override fun onSuccess(result: Flow<List<CharacterEntity>>) {
                //_uiAllCharacters.postValue(characterMapper.toUiProfileList(result.first()))
            }

            override fun onError(errorModel: ErrorModel) {
                _errorMessage.value = errorModel.message
            }

        })
    }
}