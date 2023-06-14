package com.mertoenjosh.questprovider.data.repositories

import com.mertoenjosh.questprovider.data.database.dao.AuthDao
import com.mertoenjosh.questprovider.data.network.apis.AuthApi
import com.mertoenjosh.questprovider.data.network.models.response.UserDTO
import com.mertoenjosh.questprovider.data.repositories.mappers.toEntity
import com.mertoenjosh.questprovider.data.repositories.mappers.toPayload
import com.mertoenjosh.questprovider.data.repositories.mappers.toUser
import com.mertoenjosh.questprovider.domain.models.BaseDomainModel
import com.mertoenjosh.questprovider.domain.models.User
import com.mertoenjosh.questprovider.domain.repositories.AuthRepo
import com.mertoenjosh.questprovider.util.Helpers
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    private val authApi: AuthApi,
    private val authDao: AuthDao,
) : AuthRepo {
    override suspend fun loginUser(user: User): BaseDomainModel<User> {
        val response = authApi.login(user.toPayload())

        return if (response.isSuccessful) {
            val userResponse = response.body()!!
            cacheUser(userResponse.data)

            BaseDomainModel(
                error = false,
                message = userResponse.message,
                data = userResponse.data.toUser()
            )
        } else {
            val errorResponse = Helpers.getErrorResponse(response.errorBody())

            BaseDomainModel(
                error = true,
                message = errorResponse.message,
                data = null
            )
        }
    }

    private suspend fun cacheUser(userDTO: UserDTO) {
        authDao.insertUser(userDTO.toEntity())
    }
}