package com.huaxi.dev.base.app

import android.app.Application
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.tencent.mmkv.MMKV

open class BaseApplication : Application() {

    companion object {
        private const val TAG = "LifeCycle"
    }

    override fun onCreate() {
        super.onCreate()
        // 监听应用生命周期（前后台切换）
        ProcessLifecycleOwner.get().lifecycle.addObserver(AppLifecycleObserver())
        // 初始化 MMKV
        MMKV.initialize(this)
        Log.i(TAG, "BaseApplication onCreate: initialization complete")
    }

    // 使用 DefaultLifecycleObserver 监听生命周期
    private class AppLifecycleObserver : DefaultLifecycleObserver {

        override fun onStart(owner: LifecycleOwner) {
            Log.i(TAG, "App entered foreground")
        }

        override fun onStop(owner: LifecycleOwner) {
            Log.i(TAG, "App entered background")
        }
    }
}