package com.huaxi.dev.base.net.okhttp.token

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        // 防止死循环
        if (responseCount(response) >= 3) return null

        val newToken = TokenManager.refreshTokenBlocking() ?: return null

        return response.request.newBuilder()
            .header("Authorization", "Bearer $newToken")
            .build()
    }

    private fun responseCount(response: Response): Int {
        var count = 1
        var current = response
        while (current.priorResponse != null) {
            count++
            current = current.priorResponse!!
        }
        return count
    }
}
