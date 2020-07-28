package com.vandana.gojekgitapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.vandana.gojekgitapp.GoJekApplication
import com.vandana.gojekgitapp.di.component.DaggerFragmentComponent
import com.vandana.gojekgitapp.di.component.FragmentComponent
import com.vandana.gojekgitapp.di.module.FragmentModule
import javax.inject.Inject

abstract class BaseFragment <VM : BaseViewModel> : Fragment() {

    @Inject
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildFragmentComponent())
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        setupObservers()
        viewModel.onCreate()
    }

    private fun buildFragmentComponent() =
        DaggerFragmentComponent
            .builder()
            .applicationComponent((requireContext().applicationContext as GoJekApplication).applicationComponent)
            .fragmentModule(FragmentModule(this))
            .build()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(provideLayoutId(), container, false)

    //written OPEN so that this method can be overridden
    open fun setupObservers() {
        viewModel.messageString.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })

        viewModel.messageStringId.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
    }


    fun showMessage(message: String) = Toast.makeText(context, message, Toast.LENGTH_SHORT).show()



    fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))



    @LayoutRes
    protected abstract fun provideLayoutId(): Int

    protected abstract fun injectDependencies(fragmentComponent: FragmentComponent)

    protected abstract fun setupView(view: View)

}