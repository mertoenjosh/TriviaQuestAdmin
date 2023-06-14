package com.mertoenjosh.questprovider.domain.repositories

import com.mertoenjosh.questprovider.domain.models.BaseDomainModel
import com.mertoenjosh.questprovider.domain.models.User

interface AuthRepo {
    suspend fun loginUser(user: User): BaseDomainModel<User>
    suspend fun registerUser(user: User): BaseDomainModel<User>
}