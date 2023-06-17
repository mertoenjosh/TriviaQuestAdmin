package com.mertoenjosh.questprovider.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mertoenjosh.questprovider.data.util.Constants.USERS_TABLE

@Entity(USERS_TABLE)
class UserEntity (
    @PrimaryKey
    val id: String,
    val token: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val role: String,
)