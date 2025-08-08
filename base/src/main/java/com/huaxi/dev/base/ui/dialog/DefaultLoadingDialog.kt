package com.huaxi.dev.base.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ProgressBar

class DefaultLoadingDialog(context: Context) : Dialog(context), ILoadingDialog {

    init {
        // 设置无标题、无边框透明背景
        window?.apply {
            // 透明背景
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            // 让对话框居中
            setGravity(Gravity.CENTER)
            // 去除默认的边距
            setLayout(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            // 设置窗口模糊背景（可选）
            // addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND)
        }

        // 创建一个全透明的 FrameLayout 容器，放入进度条居中显示
        val container = FrameLayout(context).apply {
            // 设置半透明黑色背景遮罩 (40%黑)
            setBackgroundColor(Color.parseColor("#66000000"))
            // 内边距，防止过小
            setPadding(60, 60, 60, 60)
            // 宽高包裹内容
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            )
        }

        // 创建系统默认圆形进度条
        val progressBar = ProgressBar(context).apply {
            isIndeterminate = true
            // 设置大小，默认48dp
            val sizeInPx = (48 * context.resources.displayMetrics.density).toInt()
            layoutParams = FrameLayout.LayoutParams(sizeInPx, sizeInPx).apply {
                gravity = Gravity.CENTER
            }
        }

        container.addView(progressBar)

        setContentView(container)
        setCancelable(false) // 不允许点击外部关闭
        setCanceledOnTouchOutside(false)
    }
}
