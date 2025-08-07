package com.huaxi.dev.base.net.okhttp.token

import com.huaxi.dev.base.net.env.EnvironmentManager
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject

object TokenManager {
    @Volatile
    private var accessToken: String? = null

    @Volatile
    private var refreshToken: String? = null

    private val lock = Object()  // 控制并发刷新

    fun getAccessToken(): String? = accessToken

    /**
     * 登录后初始化 token
     * fun onLoginSuccess(accessToken: String, refreshToken: String) {
     *     TokenManager.updateToken(accessToken, refreshToken)
     * }
     */
    fun updateToken(newAccess: String, newRefresh: String) {
        accessToken = newAccess
        refreshToken = newRefresh
    }

    /**
     * 登出清理掉token
     */
    fun clearToken() {
        accessToken = null
        refreshToken = null
    }

    fun refreshTokenBlocking(): String? {
        synchronized(lock) {
            // 如果已经有新 token，直接返回（说明其他线程刚刚刷新过）
            accessToken?.let { return it }

            val client = OkHttpClient()

            val body = """
                {
                  "grant_type": "refresh_token",
                  "refresh_token": "$refreshToken"
                }
            """.trimIndent()

            val request = Request.Builder()
                .url("${EnvironmentManager.getCurrentEnvironment().baseUrl}/getToken/")
                .post(RequestBody.create("application/json".toMediaType(), body))
                .build()

            val response = try {
                client.newCall(request).execute()
            } catch (e: Exception) {
                e.printStackTrace()
                return null
            }

            if (response.isSuccessful) {
                val responseBody = response.body?.string()
                // 假设后端返回格式如下：
                // {"access_token":"xxx", "refresh_token":"yyy"}
                val json = JSONObject(responseBody ?: return null)
                val newAccess = json.optString("access_token")
                val newRefresh = json.optString("refresh_token")

                if (newAccess.isNotEmpty() && newRefresh.isNotEmpty()) {
                    updateToken(newAccess, newRefresh)
                    return newAccess
                }
            }

            // 刷新失败
            clearToken()
            return null
        }
    }
}
