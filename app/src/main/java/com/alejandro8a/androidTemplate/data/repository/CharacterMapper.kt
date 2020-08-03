package com.alejandro8a.androidTemplate.data.repository

import com.alejandro8a.androidTemplate.network.model.CharacterResponse
import com.alejandro8a.androidTemplate.presentation.profile.UiProfile

class CharacterMapper {

    fun toUiProfile(characterResponse: CharacterResponse) =
        UiProfile(
            characterResponse.allies,
            characterResponse.enemies,
            characterResponse.photoUrl ?: DEFAULT_IMAGE,
            characterResponse.name,
            characterResponse.gender ?: EMPTY_STRING,
            characterResponse.love ?: NO_LOVE,
            characterResponse.weapon ?: DEFAULT_WEAPON
        )

    companion object {
        private const val NO_LOVE = "No love"
        private const val DEFAULT_IMAGE = "https://upload.wikimedia.org/wikipedia/en/7/79/Avatar_The_Last_Airbender_Team_Avatar_Tales_cover.jpg"
        private const val DEFAULT_WEAPON = "No weapon"
        private const val EMPTY_STRING = ""
    }
}