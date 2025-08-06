package com.huaxi.dev.base.net

enum class Environment(val baseUrl: String) {
    DEV("https://dev.api.yourdomain.com/"),
    TEST("https://test.api.yourdomain.com/"),
    PROD("https://prod.api.yourdomain.com/")
}