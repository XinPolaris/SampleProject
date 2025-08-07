package com.huaxi.dev.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.huaxi.dev.base.ui.BaseActivity
import com.huaxi.dev.databinding.ActivityLoginBinding
import com.huaxi.dev.viewmodel.DefaultViewModelFactory
import kotlin.onSuccess

class LoginActivity : BaseActivity() {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    private val viewModel: LoginViewModel by viewModels { DefaultViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {
            val username = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()

            binding.progressBar.visibility = View.VISIBLE
            viewModel.login(username, password)
        }

        viewModel.loginResult.observe(this) { result ->
            binding.progressBar.visibility = View.GONE

            result.onSuccess { userProfile ->
                Toast.makeText(this, "登录成功，欢迎 ${userProfile.name}", Toast.LENGTH_SHORT).show()
            }.onFailure { error ->
                Toast.makeText(this, "登录失败：${error.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}