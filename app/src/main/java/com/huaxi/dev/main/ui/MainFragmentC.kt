package com.huaxi.dev.main.ui

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import com.huaxi.dev.base.navigator.IFragment
import com.huaxi.dev.base.ui.BaseFragment

class MainFragmentC : BaseFragment(), IFragment {

    override fun getTitle(): String = "主页面 C"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FrameLayout(requireContext()).apply {
            setBackgroundColor(Color.BLUE)
            addView(
                TextView(requireContext()).apply {
                    text = "这是主页面 C，没有后续页面"
                }, FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.CENTER
                )
            )
        }
    }
}
