package com.alejandro8a.androidTemplate.data.repository

import com.alejandro8a.androidTemplate.data.CharacterData
import com.alejandro8a.androidTemplate.presentation.UiProfile

class CharacterMapper {

    fun toUiProfile(characterData: CharacterData) = UiProfile(
        characterData.allies,
        characterData.enemies,
        characterData.photoUrl,
        characterData.name,
        characterData.gender,
        characterData.love
    )

}