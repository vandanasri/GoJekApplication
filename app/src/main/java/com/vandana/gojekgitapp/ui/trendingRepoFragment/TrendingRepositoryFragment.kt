package com.vandana.gojekgitapp.ui.trendingRepoFragment

import android.content.Context
import android.view.View
import com.vandana.gojekgitapp.R
import com.vandana.gojekgitapp.di.component.FragmentComponent
import com.vandana.gojekgitapp.ui.base.BaseFragment

class TrendingRepositoryFragment : BaseFragment<TrendingRepositoryViewModel>() {

    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun provideLayoutId(): Int = R.layout.fragment_topgit_repo

    override fun injectDependencies(fragmentComponent: FragmentComponent) = fragmentComponent.inject(this)

    override fun setupView(view: View) {
    }

    override fun setupObservers() {

    }



}