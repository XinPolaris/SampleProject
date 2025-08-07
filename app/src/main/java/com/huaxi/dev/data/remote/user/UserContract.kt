package com.huaxi.dev.data.remote.user


object UserContract {
    data class LoginRequest(val username: String, val password: String)

    data class UpdateProfileRequest(val name: String, val avatarUrl: String)
}

