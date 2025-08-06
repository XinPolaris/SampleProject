package com.huaxi.dev.data.remote.user


object UserContract {

    data class LoginRequest(val username: String, val password: String)

    data class LoginResponse(val code: Int, val message: String, val data: UserProfile?)

    data class UserProfile(val id: String, val name: String, val avatarUrl: String)

    data class UpdateProfileRequest(val name: String, val avatarUrl: String)

    data class UpdateProfileResponse(val code: Int, val message: String)

}

