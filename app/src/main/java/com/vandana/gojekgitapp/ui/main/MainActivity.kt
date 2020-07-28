package com.vandana.gojekgitapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.vandana.gojekgitapp.R
import com.vandana.gojekgitapp.di.component.ActivityComponent
import com.vandana.gojekgitapp.ui.base.BaseActivity
import com.vandana.gojekgitapp.ui.trendingRepoFragment.TrendingRepositoryFragment

class MainActivity : BaseActivity<MainViewModel>()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadFragment(TrendingRepositoryFragment())
    }

    override fun provideLayoutId(): Int = R.layout.activity_main

    override fun setupView(savedInstanceState: Bundle?) {}

    override fun injectDependencies(activityComponent: ActivityComponent) = activityComponent.inject(this)

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment, "TAG")
            .commitAllowingStateLoss()
    }
}