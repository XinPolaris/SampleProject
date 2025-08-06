package com.huaxi.dev.repository

import com.huaxi.dev.base.repository.BaseRepository
import com.huaxi.dev.data.remote.user.UserApi
import com.huaxi.dev.data.remote.user.UserContract

class UserRepository : BaseRepository() {
    private val userApi = getApiService<UserApi>()

    suspend fun login(username: String, password: String): UserContract.UserProfile {
        val response = userApi.login(UserContract.LoginRequest(username, password))
        if (response.code == 200 && response.data != null) {
            return response.data
        } else {
            throw Exception("Login failed: ${response.message}")
        }
    }
}

