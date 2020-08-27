package com.alejandro8a.androidTemplate.di

import com.alejandro8a.androidTemplate.network.ApiErrorHandle
import com.alejandro8a.androidTemplate.presentation.character.CharacterViewModel
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

val ViewModelModule = module {

    viewModel {
        CharacterViewModel(
            createCharacterRepository(get(), get()),
            createCharacterMapper(),
            createCharacterCache()
        )
    }

}