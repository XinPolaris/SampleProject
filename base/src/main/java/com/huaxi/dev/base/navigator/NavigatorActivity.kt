package com.huaxi.dev.base.navigator

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.huaxi.dev.base.ui.BaseActivity
import com.huaxi.dev.base.ui.dialog.DefaultLoadingDialog
import com.huaxi.dev.base.ui.dialog.LoadingDialogOwner
import com.huaxi.dev.base.viewmodel.BaseViewModel

/**
 *  Created by HuangXin on 2025/8/6.
 *  支持Fragment导航的Activity基类，导航功能依赖[Navigator]
 */
abstract class NavigatorActivity : BaseActivity(), LoadingDialogOwner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindLoading(this, lifecycleScope)
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

    override val viewModel: BaseViewModel? = null

    override val loadingDialog by lazy { DefaultLoadingDialog(this) }

    abstract fun getFragmentContainerId(): Int

    abstract fun onFragmentBackStackChanged(fragment: IFragment)
}
