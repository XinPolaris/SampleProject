package com.huaxi.dev.repository

import com.huaxi.dev.base.repository.BaseRepository
import com.huaxi.dev.data.model.User
import com.huaxi.dev.data.remote.user.UserApi
import com.huaxi.dev.data.remote.user.UserContract

class UserRepository : BaseRepository() {
    private val userApi = getApiService<UserApi>()

    suspend fun login(username: String, password: String): User {
        return safeCall {
            userApi.login(UserContract.LoginRequest(username, password))
        }
    }
}

