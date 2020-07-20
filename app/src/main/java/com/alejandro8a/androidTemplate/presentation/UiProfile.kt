package com.alejandro8a.androidTemplate.presentation

data class UiProfile (
    private val allies: List<String>,
    private val enemies: List<String>,
    private val photoUrl: String,
    private val name: String,
    private val gender: String,
    private val love: String
)