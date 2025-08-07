package com.huaxi.dev.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.huaxi.dev.login.LoginViewModel
import com.huaxi.dev.repository.UserRepository

class DefaultViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                val repo = UserRepository()
                LoginViewModel(repo) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
