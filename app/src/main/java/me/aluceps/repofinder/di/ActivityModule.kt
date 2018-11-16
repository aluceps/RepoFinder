package me.aluceps.repofinder.di

import android.content.Context
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import me.aluceps.repofinder.R

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    fun provideActivity(): AppCompatActivity = activity

    @Provides
    fun provideCustomTabsIntent(context: Context): CustomTabsIntent = CustomTabsIntent.Builder()
        .setShowTitle(true)
        .setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
        .enableUrlBarHiding()
        .build()
}