package com.huaxi.dev.base.net

import com.blankj.utilcode.util.ToastUtils
import com.huaxi.dev.base.data.exception.ApiException

object GlobalErrorHandler {

    fun handleBusinessError(code: Int, message: String) {
        // TODO: 业务失败，例如code == 401自动跳登录，或者Toast提示
        showToast("请求失败: $message")
    }

    fun handleException(e: Throwable) {
        when (e) {
            is java.net.UnknownHostException -> showToast("网络异常，请检查网络连接")
            is java.net.SocketTimeoutException -> showToast("请求超时")
            is ApiException -> {} // 已处理
            else -> showToast("未知错误: ${e.message}")
        }
    }

    private fun showToast(msg: String) {
        // 替换为你项目中的 Toast 工具类
        ToastUtils.showShort(msg)
    }
}
