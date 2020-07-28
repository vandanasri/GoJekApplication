package com.vandana.gojekgitapp.di.component

import com.vandana.gojekgitapp.di.ActivityScope
import com.vandana.gojekgitapp.di.module.ActivityModule
import com.vandana.gojekgitapp.ui.main.MainActivity
import dagger.Component


@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)

}