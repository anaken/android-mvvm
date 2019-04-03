package ru.mvvm.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.AndroidInjection
import ru.mvvm.ui.Layout

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity() {

    lateinit var viewDataBinding: T

    var mViewModel: V? = null

    abstract fun getBindingVariable(): Int

    abstract fun getViewModel(): V

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mViewModel = getViewModel()
    }

    protected fun getLayoutId() = (javaClass.getAnnotation(Layout::class.java) as Layout).id

    protected fun getMenuId() = (javaClass.getAnnotation(Layout::class.java) as Layout).menu
}