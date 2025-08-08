package com.huaxi.dev.base.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 *  Created by HuangXin on 2025/8/8.
 */
inline fun <T> LifecycleOwner.collectWithLifecycle(
    flow: Flow<T>,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline action: suspend (T) -> Unit
) {
    lifecycleScope.launch {
        repeatOnLifecycle(state) {
            flow.collect { action(it) }
        }
    }
}
