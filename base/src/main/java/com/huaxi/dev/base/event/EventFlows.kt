package com.huaxi.dev.base.event

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * EventFlows.kt
 *
 * 提供两个常用的 Kotlin Flow 工厂函数，用于创建事件流和状态流：
 *
 * 1. eventFlow()
 *    - 创建不缓存历史的 MutableSharedFlow，适合一次性事件通知，如 Toast、导航事件等。
 *    - replay = 0，extraBufferCapacity = 1，避免丢失快速连续事件。
 *
 * 2. stateFlow(initialValue)
 *    - 创建持有当前状态的 MutableStateFlow，适合保存有初始值的状态，如登录状态、主题模式等。
 *
 * 该文件的函数是顶层声明，可直接调用，无需额外对象包装。
 *
 * 使用示例：
 * ```
 * private val _toastEvent = eventFlow<String>()
 * val toastEvent: SharedFlow<String> = _toastEvent
 *
 * private val _loginState = stateFlow(false)
 * val loginState: StateFlow<Boolean> = _loginState
 * ```
 *
 * Created by HuangXin on 2025/8/8.
 */

fun <T> eventFlow(): MutableSharedFlow<T> =
    MutableSharedFlow(replay = 0, extraBufferCapacity = 1)

fun <T> stateFlow(initialValue: T): MutableStateFlow<T> =
    MutableStateFlow(initialValue)
