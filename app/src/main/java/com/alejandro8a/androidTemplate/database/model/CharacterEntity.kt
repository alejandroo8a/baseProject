package com.alejandro8a.androidTemplate.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alejandro8a.androidTemplate.network.model.CharacterResponse
import com.alejandro8a.androidTemplate.database.CHARACTER_TABLE_NAME
import com.alejandro8a.androidTemplate.database.EMPTY_FIELD
import com.alejandro8a.androidTemplate.network.DomainMapper

@Entity(tableName = CHARACTER_TABLE_NAME)
data class CharacterEntity (
    @PrimaryKey val idCharacter: Int = 0,
    val photoUrl: String? = EMPTY_FIELD,
    val name: String = EMPTY_FIELD,
    val gender: String? = EMPTY_FIELD,
    val love: String? = EMPTY_FIELD,
    val weapon: String? = EMPTY_FIELD
) : DomainMapper<CharacterResponse> {

    override fun mapToDomainModel()  =
        CharacterResponse(
            idCharacter.toString(),
            emptyList(),
            emptyList(),
            photoUrl,
            name,
            gender,
            love,
            weapon
        )

}