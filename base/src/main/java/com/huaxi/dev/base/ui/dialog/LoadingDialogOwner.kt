package com.huaxi.dev.base.ui.dialog

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.huaxi.dev.base.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by HuangXin on 2025/8/6.
 * 让MMVM架构下的Activity、Fragment方便显示loading，减少模板代码
 * 为了解耦，使用设计接口而不用基类继承
 */
interface LoadingDialogOwner {

    val loadingDialog: ILoadingDialog
    val viewModel: BaseViewModel?

    // 需要生命周期作用域，传入 LifecycleOwner 和 CoroutineScope
    fun bindLoading(lifecycleOwner: LifecycleOwner, coroutineScope: CoroutineScope) {
        viewModel?.let { vm ->
            coroutineScope.launch {
                lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    vm.loading.collect { loading ->
                        if (loading) loadingDialog.show() else loadingDialog.dismiss()
                    }
                }
            }
        }
    }
}
