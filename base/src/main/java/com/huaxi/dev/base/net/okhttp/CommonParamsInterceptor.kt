package com.huaxi.dev.base.net.okhttp

import android.os.Build
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject

class CommonParamsInterceptor(
    private val paramsProvider: ParamsProvider
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val method = original.method
        val contentType = original.body?.contentType()
        val url = original.url
        val commonParams = paramsProvider.provide()

        return when {
            method == "GET" -> {
                val newUrl = url.newBuilder().apply {
                    commonParams.forEach { (key, value) ->
                        addQueryParameter(key, value)
                    }
                }.build()

                val newRequest = original.newBuilder().url(newUrl).build()
                chain.proceed(newRequest)
            }

            method == "POST" && contentType?.subtype == "json" -> {
                val originalBodyStr = original.body?.let { body ->
                    val buffer = okio.Buffer()
                    body.writeTo(buffer)
                    buffer.readUtf8()
                } ?: "{}"

                val originalJson = JSONObject(originalBodyStr)
                commonParams.forEach { (k, v) -> originalJson.put(k, v) }

                val newBody = originalJson.toString()
                    .toRequestBody("application/json; charset=utf-8".toMediaType())

                val newRequest = original.newBuilder().post(newBody).build()
                chain.proceed(newRequest)
            }

            method == "POST" && contentType?.subtype == "x-www-form-urlencoded" -> {
                val oldBody = original.body as? FormBody
                val newFormBody = FormBody.Builder().apply {
                    if (oldBody != null) {
                        for (i in 0 until oldBody.size) {
                            add(oldBody.name(i), oldBody.value(i))
                        }
                    }
                    commonParams.forEach { (k, v) -> add(k, v) }
                }.build()

                val newRequest = original.newBuilder().post(newFormBody).build()
                chain.proceed(newRequest)
            }

            else -> {
                chain.proceed(original)
            }
        }
    }
}


interface ParamsProvider {
    fun provide(): Map<String, String>
}

object DefaultParamsProvider : ParamsProvider {
    override fun provide(): Map<String, String> {
        return buildMap {
            put("user_id", "123456")
            put("device_id", "device_001")
            put("os_version", Build.VERSION.RELEASE)
            // 更多默认参数……
        }
    }
}
