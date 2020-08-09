package com.alejandro8a.androidTemplate.di

import com.alejandro8a.androidTemplate.presentation.profile.ProfileViewModel
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

val ViewModelModule = module {

    viewModel {
        ProfileViewModel(
            createGetCharacterUseCase(
                createCharacterRepository(get(), get()),
                createApiErrorHandle()
            ),
            createSaveCharacterUseCase(
                createCharacterRepository(get(), get()),
                createApiErrorHandle()
            ),
            createGetAllCharactersUseCase(
                createCharacterRepository(get(), get()),
                createApiErrorHandle()
            ),
            createCharacterMapper(),
            createCharacterCache()
        )
    }

}