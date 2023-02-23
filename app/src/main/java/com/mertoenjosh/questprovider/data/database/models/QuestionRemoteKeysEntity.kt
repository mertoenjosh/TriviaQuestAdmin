package com.mertoenjosh.questprovider.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mertoenjosh.questprovider.util.Constants
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = Constants.REMOTE_KEYS_TABLE)
data class QuestionRemoteKeysEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)