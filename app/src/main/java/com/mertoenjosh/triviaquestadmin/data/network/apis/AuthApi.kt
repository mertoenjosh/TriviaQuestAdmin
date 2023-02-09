package com.mertoenjosh.triviaquestadmin.data.network.apis

import com.mertoenjosh.triviaquestadmin.data.models.request.LoginRequest
import com.mertoenjosh.triviaquestadmin.data.models.request.UserRequest
import com.mertoenjosh.triviaquestadmin.data.models.response.UserResponse
import retrofit2.Call
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
    ): UserResponse
}