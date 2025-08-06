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

class MainFragmentB : BaseFragment(), IFragment {

    override fun getTitle(): String = "主页面 B"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val button = AppCompatButton(ContextThemeWrapper(requireContext(), com.huaxi.dev.ui.style.R.style.AppButton)).apply {
            text = "跳转到 C"
            setOnClickListener {
                Navigator.get(requireContext()).navigate(MainFragmentC::class)
            }
        }

        return FrameLayout(requireContext()).apply {
            setBackgroundColor(Color.GREEN)
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
