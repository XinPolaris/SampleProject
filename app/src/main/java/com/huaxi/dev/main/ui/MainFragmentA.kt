package com.huaxi.dev.main.ui

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.AppCompatButton
import com.huaxi.dev.base.navigator.IFragment
import com.huaxi.dev.base.navigator.Navigator
import com.huaxi.dev.base.ui.BaseFragment

class MainFragmentA : BaseFragment(), IFragment {

    override fun getTitle(): String = "主页面 A"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 创建一个按钮，点击跳转B
        val button = AppCompatButton(ContextThemeWrapper(requireContext(), com.huaxi.dev.ui.style.R.style.AppButton)).apply {
            text = "跳转到 B"
            setOnClickListener {
                Navigator.get(requireContext()).navigate(MainFragmentB::class)
            }
        }

        return FrameLayout(requireContext()).apply {
            setBackgroundColor(Color.YELLOW)
            addView(
                button, FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.CENTER
                )
            )
        }
    }
}
