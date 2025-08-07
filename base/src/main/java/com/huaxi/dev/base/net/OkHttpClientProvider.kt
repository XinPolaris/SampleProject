package com.huaxi.dev.base.net

import android.util.Log
import com.huaxi.dev.base.net.okhttp.CommonParamsInterceptor
import com.huaxi.dev.base.net.okhttp.DefaultParamsProvider
import com.huaxi.dev.base.net.okhttp.token.TokenAuthenticator
import com.huaxi.dev.base.net.okhttp.token.TokenInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

/**
 *  Created by HuangXin on 2025/8/7.
 *
 *  提供一个单例的 OkHttpClient 实例，包含公共拦截器和配置。
 *  需要修改的项：
 *  *  1.[DefaultParamsProvider]添加请求通用参数
 *  *  2.[TokenInterceptor]添加 token 头
 *  *  3.[TokenAuthenticator]处理 401 错误自动刷新 token
 *  *  4.[com.huaxi.dev.base.net.okhttp.token.TokenManager.updateToken]登录成功更新token，登出也记得清理token
 *
 *
 */
object OkHttpClientProvider {

    val client: OkHttpClient by lazy {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Log.d("OkHttp", message)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(CommonParamsInterceptor(DefaultParamsProvider))
            .addInterceptor(TokenInterceptor()) // 添加 token 头
            .authenticator(TokenAuthenticator()) // 处理 401 自动刷新
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            // 你可以这里添加更多公共拦截器或配置
            .build()
    }
}
