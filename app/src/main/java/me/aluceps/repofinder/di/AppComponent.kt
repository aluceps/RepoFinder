package me.aluceps.repofinder.di

import dagger.Component
import me.aluceps.repofinder.MyApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class
])
interface AppComponent {

    fun inject(application: MyApplication)
}