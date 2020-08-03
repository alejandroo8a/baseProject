package com.alejandro8a.androidTemplate.presentation.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.alejandro8a.androidTemplate.network.model.CharacterResponse
import com.alejandro8a.androidTemplate.data.repository.CharacterMapper
import com.alejandro8a.androidTemplate.domain.usecase.CharacterUseCase
import com.alejandro8a.androidTemplate.domain.usecase.base.UseCaseResponse
import com.alejandro8a.androidTemplate.extensions.asLiveData
import com.alejandro8a.androidTemplate.network.ErrorModel
import com.alejandro8a.androidTemplate.presentation.base.BaseViewModel

class ProfileViewModel constructor(
    private val characterUseCase: CharacterUseCase,
    private val characterMapper: CharacterMapper
) : BaseViewModel() {

    private val TAG = ProfileViewModel::class.java.name

    private val _uiCharacter = MutableLiveData<UiProfile>()
    val uiCharacter = _uiCharacter.asLiveData()

    private val _showProgressBar = MutableLiveData<Boolean>()
    val showProgressBar = _showProgressBar.asLiveData()

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage = _errorMessage.asLiveData()

    fun getCharacter() {
        _showProgressBar.value = true
        characterUseCase.invoke(scope, null, object : UseCaseResponse<List<CharacterResponse>> {
            override fun onSuccess(result: List<CharacterResponse>) {
                _showProgressBar.value = false
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

    }
}