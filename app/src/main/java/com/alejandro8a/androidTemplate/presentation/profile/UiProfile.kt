package com.alejandro8a.androidTemplate.presentation.profile

data class UiProfile (
    val allies: List<String>,
    val enemies: List<String>,
    val photoUrl: String,
    val name: String,
    val gender: String,
    val love: String
)