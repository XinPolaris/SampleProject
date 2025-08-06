package com.huaxi.dev.base.net


/**
 *  Created by HuangXin on 2025/8/6.
 */
object EnvironmentManager {
    // 默认环境
    private var currentEnv: Environment = Environment.PROD

    fun getCurrentEnvironment(): Environment = currentEnv

    fun setEnvironment(env: Environment) {
        if (currentEnv != env) {
            currentEnv = env
            RetrofitProvider.refreshRetrofit()
        }
    }
}

