package com.huaxi.dev.base.net.env

import com.huaxi.dev.base.BuildConfig
import com.huaxi.dev.base.net.RetrofitProvider


/**
 *  Created by HuangXin on 2025/8/6.
 */
object EnvironmentManager {
    // 默认环境
    private var currentEnv =
        if (BuildConfig.DEBUG) Environment.TEST else Environment.PROD

    fun getCurrentEnvironment(): Environment = currentEnv

    fun setEnvironment(env: Environment) {
        if (currentEnv != env) {
            currentEnv = env
            RetrofitProvider.refreshRetrofit()
        }
    }
}

