package com.huaxi.dev.base.navigator

import android.os.Bundle
import com.huaxi.dev.base.ui.BaseActivity

/**
 *  Created by HuangXin on 2025/8/6.
 */
abstract class NavigatorActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 监听fragment的返回栈变化
        supportFragmentManager.addOnBackStackChangedListener {
            (supportFragmentManager
                .findFragmentById(getFragmentContainerId()) as? IFragment)?.let {
                onFragmentBackStackChanged(it)
            }
        }
    }

    override fun onBackPressed() {
        val navigator = Navigator(this)
        val handled = navigator.goBack()
        if (!handled) {
            super.onBackPressed()
        }
    }

    abstract fun getFragmentContainerId(): Int

    abstract fun onFragmentBackStackChanged(fragment: IFragment)
}
