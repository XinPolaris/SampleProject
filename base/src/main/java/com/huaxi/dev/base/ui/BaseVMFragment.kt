package com.huaxi.dev.base.ui

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.huaxi.dev.base.ui.dialog.DefaultLoadingDialog
import com.huaxi.dev.base.ui.dialog.LoadingDialogOwner
import com.huaxi.dev.base.viewmodel.BaseViewModel

/**
 *  Created by HuangXin on 2025/8/6.
 *  MVVM下Fragment基类
 */
abstract class BaseVMFragment<VM : BaseViewModel> : BaseFragment(), LoadingDialogOwner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindLoading(this, lifecycleScope)
    }

    override val loadingDialog by lazy { DefaultLoadingDialog(requireContext()) }

    abstract override val viewModel: VM
}