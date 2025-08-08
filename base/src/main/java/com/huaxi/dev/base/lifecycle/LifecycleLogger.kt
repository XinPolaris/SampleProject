package com.huaxi.dev.base.lifecycle

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 *  Created by HuangXin on 2025/8/6.
 */
interface LifecycleLogger

/**
 * 打印生命周期
 */
fun LifecycleLogger.logLifecycle(owner: LifecycleOwner) {
    val tag = "LifeCycle"
    val identity = "${owner.javaClass.simpleName}@${owner.hashCode().toString(16)}"

    owner.lifecycle.addObserver(object : DefaultLifecycleObserver {
        override fun onCreate(owner: LifecycleOwner) {
            Log.d(tag, "$identity → onCreate")
        }

        override fun onStart(owner: LifecycleOwner) {
            Log.d(tag, "$identity → onStart")
        }

        override fun onResume(owner: LifecycleOwner) {
            Log.d(tag, "$identity → onResume")
        }

        override fun onPause(owner: LifecycleOwner) {
            Log.d(tag, "$identity → onPause")
        }

        override fun onStop(owner: LifecycleOwner) {
            Log.d(tag, "$identity → onStop")
        }

        override fun onDestroy(owner: LifecycleOwner) {
            Log.d(tag, "$identity → onDestroy")
        }
    })
}
