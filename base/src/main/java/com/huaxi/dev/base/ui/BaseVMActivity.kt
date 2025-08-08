package com.huaxi.dev.base.ui

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.huaxi.dev.base.ui.dialog.DefaultLoadingDialog
import com.huaxi.dev.base.ui.dialog.LoadingDialogOwner
import com.huaxi.dev.base.viewmodel.BaseViewModel

/**
 *  Created by HuangXin on 2025/8/6.
 *  MVVM下Activity基类
 *  处理通用UI逻辑，比如显示loading对话框等
 */
abstract class BaseVMActivity<VM : BaseViewModel> : BaseActivity(), LoadingDialogOwner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindLoading(this, lifecycleScope)
    }

    override val loadingDialog by lazy { DefaultLoadingDialog(this) }

    abstract override val viewModel: VM
}