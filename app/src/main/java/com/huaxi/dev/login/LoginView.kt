package com.huaxi.dev.login

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.huaxi.dev.base.lifecycle.collectWithLifecycle
import com.huaxi.dev.base.ui.BaseVMView
import com.huaxi.dev.main.MainActivity
import com.huaxi.dev.viewmodel.DefaultViewModelFactory

class LoginView(context: Context, attrs: AttributeSet? = null) : BaseVMView(context, attrs) {

    private val viewModel: LoginViewModel by viewModels {
        DefaultViewModelFactory()
    }

    init {
        observeLoginResult()
    }

    private fun observeLoginResult() {
        collectWithLifecycle(viewModel.loginResult) { result ->
            result?.onSuccess { user ->
                Toast.makeText(
                    context, "欢迎，${user.name}", Toast.LENGTH_SHORT
                ).show()
                // 跳转主页
                context.startActivity(Intent(requireContext(), MainActivity::class.java))
            }?.onFailure { e ->
                Toast.makeText(
                    context, "登录失败: ${e.message}", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
