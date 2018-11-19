package me.aluceps.repofinder.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: Application) {

    @Provides
    fun provideContext(): Context = app

    @Provides
    fun providePreferences(context: Context): SharedPreferences =
            context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
}