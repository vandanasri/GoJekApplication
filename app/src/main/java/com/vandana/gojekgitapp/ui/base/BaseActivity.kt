package com.vandana.gojekgitapp.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.vandana.gojekgitapp.GoJekApplication
import com.vandana.gojekgitapp.di.component.ActivityComponent
import com.vandana.gojekgitapp.di.component.DaggerActivityComponent
import com.vandana.gojekgitapp.di.module.ActivityModule
import javax.inject.Inject


abstract class BaseActivity <VM : BaseViewModel> : AppCompatActivity() {

    @Inject
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildActivityComponent()) // inject dependency before calling any view.
        super.onCreate(savedInstanceState)
        setContentView(provideLayoutId())
        setupView(savedInstanceState)
        viewModel.onCreate()
    }


    private fun buildActivityComponent() =
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as GoJekApplication).applicationComponent)
            .activityModule(ActivityModule(this))
           .build()


    fun showMessage(message: String) =
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()

    fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))

    @LayoutRes
    protected abstract fun provideLayoutId(): Int


    protected abstract fun setupView(savedInstanceState: Bundle?)

    //to inject dagger dependencies
    protected abstract fun injectDependencies(activityComponent: ActivityComponent)
}