package com.huaxi.dev.base.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.huaxi.dev.base.lifecycle.LifecycleLogger
import com.huaxi.dev.base.lifecycle.logLifecycle

/**
 *  Created by HuangXin on 2025/8/6.
 */
open class BaseFragment : Fragment(), LifecycleLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logLifecycle(this)
    }
}