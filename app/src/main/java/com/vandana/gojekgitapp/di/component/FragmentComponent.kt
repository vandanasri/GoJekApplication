package com.vandana.gojekgitapp.di.component

import com.vandana.gojekgitapp.di.FragmentScope
import com.vandana.gojekgitapp.di.module.FragmentModule
import com.vandana.gojekgitapp.ui.trendingRepoFragment.TrendingRepositoryFragment
import dagger.Component


@FragmentScope
@Component(dependencies = [ApplicationComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject(fragment: TrendingRepositoryFragment)
}