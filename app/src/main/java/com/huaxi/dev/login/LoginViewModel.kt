package com.huaxi.dev.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huaxi.dev.data.remote.user.UserContract
import com.huaxi.dev.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<Result<UserContract.UserProfile>>()
    val loginResult: LiveData<Result<UserContract.UserProfile>> = _loginResult

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val userProfile = repository.login(username, password)
                _loginResult.value = Result.success(userProfile)
            } catch (e: Exception) {
                _loginResult.value = Result.failure(e)
            }
        }
    }
}
