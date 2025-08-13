package com.huaxi.dev.base.ui

import android.app.Application
import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.MainThread
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import kotlin.reflect.KClass

abstract class BaseVMView : FrameLayout, ViewModelStoreOwner, LifecycleOwner {

    private val internalViewModelStore = ViewModelStore()
    private val lifecycleRegistry = LifecycleRegistry(this)

    // ----------------- 构造方法 -----------------
    constructor(context: Context) : super(context) {
        initLifecycle()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initLifecycle()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initLifecycle()
    }

    private fun initLifecycle() {
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
    }

    // ----------------- ViewModelStoreOwner & LifecycleOwner -----------------
    override val viewModelStore: ViewModelStore
        get() = internalViewModelStore

    override val lifecycle: Lifecycle
        get() = lifecycleRegistry

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
        internalViewModelStore.clear()
    }

    // ----------------- viewModels 支持 -----------------
    @MainThread
    protected inline fun <reified VM : ViewModel> viewModels(
        noinline ownerProducer: () -> ViewModelStoreOwner = { this },
        noinline extrasProducer: (() -> CreationExtras)? = null,
        noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
    ): Lazy<VM> {
        val owner by lazy(LazyThreadSafetyMode.NONE) { ownerProducer() }

        return createViewModelLazy(
            VM::class,
            { owner.viewModelStore },
            {
                extrasProducer?.invoke()
                    ?: (owner as? HasDefaultViewModelProviderFactory)?.defaultViewModelCreationExtras
                    ?: CreationExtras.Empty
            },
            factoryProducer ?: {
                (owner as? HasDefaultViewModelProviderFactory)?.defaultViewModelProviderFactory
                    ?: defaultFactoryFromContext(context.applicationContext)
            }
        )
    }

    protected fun defaultFactoryFromContext(appContext: Context): ViewModelProvider.Factory {
        return (appContext as? Application)?.let {
            ViewModelProvider.AndroidViewModelFactory.getInstance(it)
        } ?: ViewModelProvider.NewInstanceFactory()
    }

    protected fun <VM : ViewModel> createViewModelLazy(
        viewModelClass: KClass<VM>,
        storeProducer: () -> ViewModelStore,
        extrasProducer: () -> CreationExtras = { CreationExtras.Empty },
        factoryProducer: () -> ViewModelProvider.Factory
    ): Lazy<VM> {
        return lazy(LazyThreadSafetyMode.NONE) {
            val store = storeProducer()
            val factory = factoryProducer()
            ViewModelProvider(store, factory, extrasProducer()).get(viewModelClass.java)
        }
    }
}
