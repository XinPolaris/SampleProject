package com.huaxi.dev.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.core.view.isVisible
import com.huaxi.dev.base.navigator.IFragment
import com.huaxi.dev.base.navigator.Navigator
import com.huaxi.dev.base.navigator.NavigatorActivity
import com.huaxi.dev.databinding.ActivityMainBinding
import com.huaxi.dev.main.ui.MainFragmentA

class MainActivity : NavigatorActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
        val mainFragment = MainFragmentA()
        Navigator.get(this).navigate(mainFragment, null, addToBackStack = false)
        onFragmentBackStackChanged(mainFragment)
    }

    @SuppressLint("MissingSuperCall", "GestureBackNavigation")
    override fun onBackPressed() {
        Navigator(this).goBack()
    }

    override fun getFragmentContainerId(): Int = binding.fragmentContainer.id
    override fun onFragmentBackStackChanged(fragment: IFragment) {
        binding.btnBack.isVisible = fragment !is MainFragmentA
        binding.tvTitle.text = fragment.getTitle()
    }
}
