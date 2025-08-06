package com.huaxi.dev.data.remote.user

import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("user/login")
    suspend fun login(@Body request: UserContract.LoginRequest): UserContract.LoginResponse

    @POST("user/updateProfile")
    suspend fun updateProfile(@Body request: UserContract.UpdateProfileRequest): UserContract.UpdateProfileResponse
}
