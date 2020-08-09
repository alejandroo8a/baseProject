package com.alejandro8a.androidTemplate.network.model

import com.alejandro8a.androidTemplate.database.model.CharacterEntity
import com.alejandro8a.androidTemplate.network.RoomMapper
import com.squareup.moshi.Json

data class CharacterResponse (
    @field:Json(name = "_id") val idCharacter: String,
    val allies: List<String>,
    val enemies: List<String>,
    val photoUrl: String?,
    val name: String,
    val gender: String?,
    val love: String?,
    val weapon: String?
) : RoomMapper<CharacterEntity> {

    override fun mapToRoomEntity() = CharacterEntity(idCharacter, photoUrl, name, gender, love, weapon)

}