package com.huaxi.dev.base.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.huaxi.dev.base.lifecycle.LifecycleLogger
import com.huaxi.dev.base.lifecycle.logLifecycle

/**
 *  Created by HuangXin on 2025/8/6.
 */
open class BaseActivity : AppCompatActivity(), LifecycleLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置屏幕方向为横屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        logLifecycle(this)
    }
}