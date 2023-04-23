package com.mertoenjosh.questprovider.data.network.apis

import com.mertoenjosh.questprovider.data.network.models.request.LoginRequest
import com.mertoenjosh.questprovider.data.network.models.request.UserRequest
import com.mertoenjosh.questprovider.data.network.models.response.BaseResponse
import com.mertoenjosh.questprovider.data.network.models.response.UserDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("users/login")
    suspend fun login(
        @Body loginObj: LoginRequest
    ): Response<BaseResponse<UserDTO>>

    @POST("users/signup")
    fun createUser(
        @Body userObj: UserRequest
    ): Response<BaseResponse<UserDTO>>
}