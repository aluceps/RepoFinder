package me.aluceps.repofinder

import android.app.Application
import me.aluceps.repofinder.di.AppComponent
import me.aluceps.repofinder.di.AppModule
import me.aluceps.repofinder.di.DaggerAppComponent

class MyApplication : Application() {

    private lateinit var component: AppComponent

    private val module: AppModule by lazy {
        AppModule(this)
    }

    fun getComponent(): AppComponent = component

    override fun onCreate() {
        super.onCreate()
        initializeInjector()
    }

    private fun initializeInjector() {
        component = DaggerAppComponent.builder()
                .appModule(module)
                .build()
        component.inject(this)
    }
}