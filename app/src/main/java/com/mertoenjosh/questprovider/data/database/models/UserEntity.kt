package com.mertoenjosh.questprovider.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mertoenjosh.questprovider.util.Constants

@Entity(Constants.USERS_TABLE)
class UserEntity (
    @PrimaryKey
    val id: String, // 637c82a52064d5546ed1ee2e
    val token: String,
    val email: String, // test@mnt.dev
    val name: String, // test
    val role: String, // user
)