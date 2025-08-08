package com.huaxi.dev.login

import com.huaxi.dev.base.viewmodel.BaseViewModel
import com.huaxi.dev.data.model.User
import com.huaxi.dev.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel(private val repository: UserRepository) : BaseViewModel() {

    private val _loginResult = MutableStateFlow<Result<User>?>(null)
    val loginResult: StateFlow<Result<User>?> = _loginResult

    fun login(username: String, password: String) {
        launchWithLoading {
            try {
                val userProfile = repository.login(username, password)
                _loginResult.value = Result.success(userProfile)
            } catch (e: Exception) {
                _loginResult.value = Result.failure(e)
            }
        }
    }

    fun login2(username: String, password: String) {
        launch {
            try {
                val userProfile = repository.login(username, password)
                _loginResult.value = Result.success(userProfile)
            } catch (e: Exception) {
                _loginResult.value = Result.failure(e)
            }
        }
    }
}
