package ru.mvvm.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import ru.mvvm.BR
import ru.mvvm.R
import ru.mvvm.databinding.ActivityMainBinding
import ru.mvvm.service.GetContactsService
import ru.mvvm.ui.Layout
import ru.mvvm.ui.base.BaseActivity
import javax.inject.Inject

@Layout(id = R.layout.activity_main)
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    private var mainViewModel: MainViewModel? = null

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): MainViewModel {
        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        return mainViewModel!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startService(Intent(this, GetContactsService::class.java))


    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        mainViewModel?.errorsData?.observe(this, Observer { throwable ->
            throwable.message?.let {
                Snackbar.make(viewDataBinding.root, it, Snackbar.LENGTH_LONG).show()
            }
        })
    }

    override fun onDestroy() {
        stopService(Intent(this, GetContactsService::class.java))
        super.onDestroy()
    }

    abstract class ViewModelProviderFactory : ViewModelProvider.NewInstanceFactory()
}
