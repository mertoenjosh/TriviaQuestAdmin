package com.mertoenjosh.triviaquestadmin.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mertoenjosh.triviaquestadmin.util.Constants
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = Constants.REMOTE_KEYS_TABLE)
data class QuestProviderRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)