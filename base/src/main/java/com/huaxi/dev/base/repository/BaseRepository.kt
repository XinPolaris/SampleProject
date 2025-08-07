package com.huaxi.dev.base.repository

import com.huaxi.dev.base.data.exception.ApiException
import com.huaxi.dev.base.data.model.ApiResponse
import com.huaxi.dev.base.net.GlobalErrorHandler
import com.huaxi.dev.base.net.RetrofitProvider

open class BaseRepository {
    protected inline fun <reified T> getApiService(): T {
        return RetrofitProvider.createService(T::class.java)
    }

    protected suspend fun <T> safeCall(
        block: suspend () -> ApiResponse<T>
    ): T {
        try {
            val response = block()
            if (response.code == 200 && response.data != null) {
                return response.data
            } else {
                // 业务失败，通知全局错误处理
                GlobalErrorHandler.handleBusinessError(response.code, response.message)
                throw ApiException(response.code, response.message)
            }
        } catch (e: Exception) {
            GlobalErrorHandler.handleException(e)
            throw e
        }
    }
}