package com.huaxi.dev.base.net

import com.huaxi.dev.base.net.env.EnvironmentManager
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {
    // 持有 Retrofit 实例，支持重新创建
    @Volatile
    private var retrofitInstance: Retrofit? = null

    private val lock = Any()

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(EnvironmentManager.getCurrentEnvironment().baseUrl)
            .client(OkHttpClientProvider.client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val retrofit: Retrofit
        get() {
            return retrofitInstance ?: synchronized(lock) {
                retrofitInstance ?: createRetrofit().also { retrofitInstance = it }
            }
        }

    fun <T> createService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }

    /**
     * 环境切换后调用此方法，刷新 Retrofit 实例
     */
    fun refreshRetrofit() {
        synchronized(lock) {
            retrofitInstance = createRetrofit()
        }
    }
}
