package com.huaxi.dev.base.navigator

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import kotlin.reflect.KClass

/**
 *  Created by HuangXin on 2025/8/6.
 */
class Navigator(
    private val activity: Activity?,
    private val fromFragment: Fragment? = null
) {

    fun navigate(
        fragmentClass: KClass<out Fragment>,
        args: Bundle? = null,
        addToBackStack: Boolean = true
    ) {
        navigate(fragmentClass.java.newInstance(), args, addToBackStack)
    }

    fun navigate(
        fragment: Fragment,
        args: Bundle? = null,
        addToBackStack: Boolean = true
    ) {
        val activity = activity as? NavigatorActivity ?: return
        val fragment = fragment.apply {
            arguments = args
        }

        val transaction = activity.supportFragmentManager.beginTransaction()
            .replace(activity.getFragmentContainerId(), fragment)

        if (addToBackStack) {
            transaction.addToBackStack(fragment.javaClass.name)
        }

        transaction.commit()
    }

    fun goBack(): Boolean {
        val activity = activity as? NavigatorActivity ?: return false
        val fm = activity.supportFragmentManager

        // 当前 Fragment 可以返回
        if (fromFragment != null && fromFragment.childFragmentManager.backStackEntryCount > 0) {
            fromFragment.childFragmentManager.popBackStack()
            return true
        }

        // Activity Fragment 栈可以返回
        if (fm.backStackEntryCount > 0) {
            fm.popBackStack()
            return true
        }
        return false
    }

    fun setFragmentResult(
        requestKey: String,
        result: Bundle
    ) {
        fromFragment?.parentFragmentManager?.setFragmentResult(requestKey, result)
    }

    fun setFragmentResultListener(
        requestKey: String,
        lifecycleOwner: LifecycleOwner,
        listener: (Bundle) -> Unit
    ) {
        val activity = activity as? FragmentActivity ?: return
        activity.supportFragmentManager.setFragmentResultListener(
            requestKey,
            lifecycleOwner
        ) { _, bundle ->
            listener(bundle)
        }
    }

    companion object {
        fun get(context: Context): Navigator {
            val fragment = (context as? FragmentActivity)
                ?.supportFragmentManager
                ?.primaryNavigationFragment

            return Navigator(context.getActivity(), fragment)
        }
    }
}

private fun Context.getActivity(): Activity? {
    var ctx = this
    while (ctx is ContextWrapper) {
        if (ctx is Activity) return ctx
        ctx = ctx.baseContext
    }
    return null
}

