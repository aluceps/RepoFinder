package me.aluceps.repofinder.di

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
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