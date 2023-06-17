package com.mertoenjosh.questprovider.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mertoenjosh.questprovider.data.util.Constants.REMOTE_KEYS_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = REMOTE_KEYS_TABLE)
data class QuestionRemoteKeysEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)