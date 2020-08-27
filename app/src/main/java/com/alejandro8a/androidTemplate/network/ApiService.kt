package com.alejandro8a.androidTemplate.network

import com.alejandro8a.androidTemplate.network.model.CharacterResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface ApiService {

    @GET("api/v1/characters/random")
    suspend fun getCharacter(): List<CharacterResponse>

}