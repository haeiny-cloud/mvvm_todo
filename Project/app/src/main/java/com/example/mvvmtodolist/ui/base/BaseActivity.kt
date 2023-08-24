package com.example.mvvmtodolist.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.mvvmtodolist.BR

abstract class BaseActivity<B: ViewDataBinding, VM: BaseViewModel> : AppCompatActivity() {

    protected lateinit var mViewDataBinding: B

    abstract val layoutId: Int
    abstract val viewModel: VM
    abstract fun setUp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        setUp()
    }

    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        mViewDataBinding.lifecycleOwner = this
        mViewDataBinding.setVariable(BR.vm, viewModel)
    }
}