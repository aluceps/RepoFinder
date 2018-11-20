package me.aluceps.repofinder

import android.app.Application
import me.aluceps.repofinder.di.AppComponent
import me.aluceps.repofinder.di.AppModule
import me.aluceps.repofinder.di.DaggerAppComponent
import timber.log.Timber

class MyApplication : Application() {

    private var component: AppComponent? = null

    private val module: AppModule by lazy {
        AppModule(this)
    }

    fun getComponent(): AppComponent? = component

    override fun onCreate() {
        super.onCreate()
        initializeInjector()
        initializeTimber()
    }

    private fun initializeInjector() {
        component = DaggerAppComponent.builder()
                .appModule(module)
                .build()
        component?.inject(this)
    }

    private fun initializeTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}