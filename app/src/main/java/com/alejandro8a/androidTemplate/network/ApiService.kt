package com.alejandro8a.androidTemplate.network

import com.alejandro8a.androidTemplate.data.CharacterData
import retrofit2.http.GET

interface ApiService {

    @GET("api/v1/characters/random")
    suspend fun getCharacter(): CharacterData

}