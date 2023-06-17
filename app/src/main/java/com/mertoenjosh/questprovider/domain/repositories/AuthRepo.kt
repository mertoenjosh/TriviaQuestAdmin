package com.mertoenjosh.questprovider.domain.repositories

import com.mertoenjosh.questprovider.domain.models.BaseDomainModel
import com.mertoenjosh.questprovider.domain.models.User
import kotlinx.coroutines.flow.Flow

interface AuthRepo {
    suspend fun loginUser(user: User): BaseDomainModel<User>
    suspend fun registerUser(user: User): BaseDomainModel<User>
    suspend fun getUser(): Flow<User>
    suspend fun deleteUser()
}