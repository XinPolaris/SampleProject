package com.huaxi.dev.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 *  Created by HuangXin on 2025/8/8.
 */
open class BaseViewModel : ViewModel() {

    protected val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    // 统一封装调用接口的函数
    protected fun launch(
        block: suspend () -> Unit
    ) = {
        viewModelScope.launch {
            block.invoke()
        }
    }

    protected fun launchWithLoading(
        showLoading: Boolean = true,
        block: suspend () -> Unit
    ) = {
        viewModelScope.launch {
            if (showLoading) _loading.value = true
            try {
                block.invoke()
            } finally {
                if (showLoading) _loading.value = false
            }
        }
    }
}
