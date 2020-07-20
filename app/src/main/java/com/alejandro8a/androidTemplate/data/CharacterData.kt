package com.alejandro8a.androidTemplate.data

data class CharacterData (
    val idCharacter: String,
    val allies: List<String>,
    val enemies: List<String>,
    val photoUrl: String,
    val name: String,
    val gender: String,
    val love: String
)