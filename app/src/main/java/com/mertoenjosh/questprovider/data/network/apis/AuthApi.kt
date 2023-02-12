package com.mertoenjosh.questprovider.data.network.apis

import com.mertoenjosh.questprovider.data.models.request.LoginRequest
import com.mertoenjosh.questprovider.data.models.request.UserRequest
import com.mertoenjosh.questprovider.data.models.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("users/login")
    suspend fun login(
        @Body loginObj: LoginRequest
    ): Response<UserResponse>

    @POST("users/signup")
    fun createUser(
        @Body userObj: UserRequest
    ): Response<UserResponse>
}