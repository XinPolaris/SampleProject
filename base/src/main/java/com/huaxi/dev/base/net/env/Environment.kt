package com.huaxi.dev.base.net.env

/**
 *  Created by HuangXin on 2025/8/6.
 *  * Represents different environments for API requests.
 */
enum class Environment(val baseUrl: String) {
    DEV("https://dev.api.yourdomain.com/"),
    TEST("https://test.api.yourdomain.com/"),
    PROD("https://prod.api.yourdomain.com/")
}