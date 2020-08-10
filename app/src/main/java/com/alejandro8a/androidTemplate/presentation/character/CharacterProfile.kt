package com.alejandro8a.androidTemplate.presentation.character

data class CharacterProfile (
    val allies: List<String>,
    val enemies: List<String>,
    val photoUrl: String,
    val name: String,
    val gender: String,
    val love: String,
    val weapon: String
)