package com.huaxi.dev.data.remote.user

import com.huaxi.dev.base.data.model.ApiResponse
import com.huaxi.dev.data.model.User
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("user/login")
    suspend fun login(@Body request: UserContract.LoginRequest): ApiResponse<User>

    @POST("user/updateProfile")
    suspend fun updateProfile(@Body request: UserContract.UpdateProfileRequest): ApiResponse<User>
}
