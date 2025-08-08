package com.huaxi.dev.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.huaxi.dev.base.lifecycle.collectWithLifecycle
import com.huaxi.dev.base.ui.BaseVMFragment
import com.huaxi.dev.base.ui.ext.viewBinding
import com.huaxi.dev.databinding.ActivityLoginBinding
import com.huaxi.dev.main.MainActivity
import com.huaxi.dev.viewmodel.DefaultViewModelFactory

class LoginFragment : BaseVMFragment<LoginViewModel>() {

    private val binding by viewBinding(ActivityLoginBinding::inflate)

    override val viewModel: LoginViewModel by viewModels { DefaultViewModelFactory() }
    //如果想跟Activity共用viewmodel
//    private val viewModel: LoginViewModel by activityViewModels { DefaultViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        observeLoginResult()
    }

    private fun initListeners() {
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            if (username.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(username, password)
            } else {
                Toast.makeText(requireContext(), "请输入用户名和密码", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeLoginResult() {
        collectWithLifecycle(viewModel.loginResult) { result ->
            result?.onSuccess { user ->
                Toast.makeText(
                    requireContext(), "欢迎，${user.name}", Toast.LENGTH_SHORT
                ).show()
                // 跳转主页
                startActivity(Intent(requireContext(), MainActivity::class.java))
            }?.onFailure { e ->
                Toast.makeText(
                    requireContext(), "登录失败: ${e.message}", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
