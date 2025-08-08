package com.huaxi.dev.base.ui.ext

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 *  Created by HuangXin on 2025/8/8.
 */

/**
 * ViewBinding委托，目的让Fragment在onDestroyView时自动清理ViewBinding，避免内存泄漏。
 */
fun <VB : ViewBinding> Fragment.viewBinding(
    inflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
) = FragmentViewBindingDelegate(inflater)

class FragmentViewBindingDelegate<VB : ViewBinding>(
    private val inflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : ReadOnlyProperty<Fragment, VB>, DefaultLifecycleObserver {

    private var binding: VB? = null
    private var lifecycleOwner: LifecycleOwner? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): VB {
        binding?.let { return it }

        val viewLifecycleOwner = thisRef.viewLifecycleOwner

        // 如果生命周期发生变化，取消之前监听，重新监听新的 lifecycle
        if (lifecycleOwner != viewLifecycleOwner) {
            lifecycleOwner?.lifecycle?.removeObserver(this)
            lifecycleOwner = viewLifecycleOwner
            lifecycleOwner?.lifecycle?.addObserver(this)
        }

        // 通过 inflate 生成 ViewBinding
        binding = inflater(thisRef.layoutInflater, null, false)
        return binding!!
    }

    // 这个回调就是对应 viewLifecycleOwner 的 onDestroy，也就是 Fragment 的 onDestroyView
    override fun onDestroy(owner: LifecycleOwner) {
        binding = null  // 在这里置空，防止内存泄漏
    }
}