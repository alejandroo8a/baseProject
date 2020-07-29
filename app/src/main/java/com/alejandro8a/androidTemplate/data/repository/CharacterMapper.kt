package com.alejandro8a.androidTemplate.data.repository

import com.alejandro8a.androidTemplate.data.CharacterData
import com.alejandro8a.androidTemplate.presentation.profile.UiProfile

class CharacterMapper {

    fun toUiProfile(characterData: CharacterData) =
        UiProfile(
            characterData.allies,
            characterData.enemies,
            characterData.photoUrl ?: DEFAULT_IMAGE,
            characterData.name,
            characterData.gender ?: EMPTY_STRING,
            characterData.love ?: NO_LOVE,
            characterData.weapon ?: DEFAULT_WEAPON
        )

    companion object {
        private const val NO_LOVE = "No love"
        private const val DEFAULT_IMAGE = "https://upload.wikimedia.org/wikipedia/en/7/79/Avatar_The_Last_Airbender_Team_Avatar_Tales_cover.jpg"
        private const val DEFAULT_WEAPON = "No weapon"
        private const val EMPTY_STRING = ""
    }
}