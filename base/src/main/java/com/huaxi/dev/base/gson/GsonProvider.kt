package com.huaxi.dev.base.gson

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.internal.bind.TypeAdapters
import com.huaxi.dev.base.gson.adapter.BigDecimalTypeAdapter
import com.huaxi.dev.base.gson.adapter.BooleanTypeAdapter
import com.huaxi.dev.base.gson.adapter.DoubleTypeAdapter
import com.huaxi.dev.base.gson.adapter.FloatTypeAdapter
import com.huaxi.dev.base.gson.adapter.IntegerTypeAdapter
import com.huaxi.dev.base.gson.adapter.LongTypeAdapter
import com.huaxi.dev.base.gson.adapter.StringTypeAdapter
import java.math.BigDecimal

/**
 * 自定义 Gson 提供类
 * Created by HuangXin on 2025/8/7.
 * 解决问题：标准 Gson 在遇到 "value": ""、null、"123"（字符串）等异常/不一致格式时，会抛出 JsonSyntaxException
 * 优点：容错性强（自适应字符串、空字符串、null、小数等不规范数据）、避免崩溃
 */
object GsonProvider {

    val instance: Gson by lazy {
        GsonBuilder()
            .disableHtmlEscaping()
            .registerTypeAdapterFactory(
                TypeAdapters.newFactory(
                    Boolean::class.java,
                    BooleanTypeAdapter()
                )
            )
            .registerTypeAdapterFactory(
                TypeAdapters.newFactory(
                    java.lang.Boolean.TYPE,
                    BooleanTypeAdapter()
                )
            )
            .registerTypeAdapterFactory(
                TypeAdapters.newFactory(
                    Int::class.java,
                    IntegerTypeAdapter()
                )
            )
            .registerTypeAdapterFactory(
                TypeAdapters.newFactory(
                    java.lang.Integer.TYPE,
                    IntegerTypeAdapter()
                )
            )
            .registerTypeAdapterFactory(
                TypeAdapters.newFactory(
                    Long::class.java,
                    LongTypeAdapter()
                )
            )
            .registerTypeAdapterFactory(
                TypeAdapters.newFactory(
                    java.lang.Long.TYPE,
                    LongTypeAdapter()
                )
            )
            .registerTypeAdapterFactory(
                TypeAdapters.newFactory(
                    Float::class.java,
                    FloatTypeAdapter()
                )
            )
            .registerTypeAdapterFactory(
                TypeAdapters.newFactory(
                    java.lang.Float.TYPE,
                    FloatTypeAdapter()
                )
            )
            .registerTypeAdapterFactory(
                TypeAdapters.newFactory(
                    Double::class.java,
                    DoubleTypeAdapter()
                )
            )
            .registerTypeAdapterFactory(
                TypeAdapters.newFactory(
                    java.lang.Double.TYPE,
                    DoubleTypeAdapter()
                )
            )
            .registerTypeAdapterFactory(
                TypeAdapters.newFactory(
                    String::class.java,
                    StringTypeAdapter()
                )
            )
            .registerTypeAdapterFactory(
                TypeAdapters.newFactory(
                    BigDecimal::class.java,
                    BigDecimalTypeAdapter()
                )
            )
            .create()
    }
}
