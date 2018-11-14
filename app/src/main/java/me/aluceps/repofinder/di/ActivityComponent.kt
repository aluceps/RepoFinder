package me.aluceps.repofinder.di

import dagger.Subcomponent
import me.aluceps.repofinder.ui.MainActivity
import me.aluceps.repofinder.di.scope.ActivityScope

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)
}