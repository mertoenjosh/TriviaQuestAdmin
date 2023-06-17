package com.mertoenjosh.questprovider.data.data_source

import com.mertoenjosh.questprovider.data.data_source.mappers.toDomain
import com.mertoenjosh.questprovider.data.data_source.mappers.toEntity
import com.mertoenjosh.questprovider.data.data_source.mappers.toLoginPayload
import com.mertoenjosh.questprovider.data.data_source.mappers.toSignupPayload
import com.mertoenjosh.questprovider.data.database.dao.AuthDao
import com.mertoenjosh.questprovider.data.network.apis.AuthApi
import com.mertoenjosh.questprovider.data.network.models.response.UserDTO
import com.mertoenjosh.questprovider.data.util.Constants.USER_ID
import com.mertoenjosh.questprovider.domain.models.BaseDomainModel
import com.mertoenjosh.questprovider.domain.models.User
import com.mertoenjosh.questprovider.domain.repositories.AuthRepo
import com.mertoenjosh.questprovider.util.Helpers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    private val authApi: AuthApi,
    private val authDao: AuthDao,
) : AuthRepo {
    override suspend fun loginUser(user: User): BaseDomainModel<User> {
        val response = authApi.login(user.toLoginPayload())

        return if (response.isSuccessful) {
            val userResponse = response.body()!!
            cacheUser(userResponse.data)

            BaseDomainModel(
                error = false,
                message = userResponse.message,
                data = userResponse.data.toDomain()
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

    override suspend fun registerUser(user: User): BaseDomainModel<User> {
        val response = authApi.createUser(user.toSignupPayload())

        return if (response.isSuccessful) {
            val userResponse = response.body()!!
            cacheUser(userResponse.data)

            BaseDomainModel(
                error = false,
                message = userResponse.message,
                data = userResponse.data.toDomain()
            )
        } else {
            val errRes = Helpers.getErrorResponse(response.errorBody())

            BaseDomainModel(
                error = true,
                message = errRes.message,
                data = null
            )
        }
    }

    override suspend fun getUser(): Flow<User> {
        return authDao.getUserWithId(USER_ID).map { it.toDomain() }
    }

    override suspend fun deleteUser() {
        authDao.deleteUser()
    }

    private suspend fun cacheUser(userDTO: UserDTO) {
        authDao.cacheUser(userDTO.toEntity())
    }
}