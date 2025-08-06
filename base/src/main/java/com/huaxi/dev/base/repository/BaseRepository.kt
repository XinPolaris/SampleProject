package com.huaxi.dev.base.repository

import com.huaxi.dev.base.net.RetrofitProvider

open class BaseRepository {
    protected inline fun <reified T> getApiService(): T {
        return RetrofitProvider.createService(T::class.java)
    }
}