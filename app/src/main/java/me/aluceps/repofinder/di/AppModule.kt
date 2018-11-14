package me.aluceps.repofinder.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class AppModule(private val app: Application) {

    @Provides
    fun provideContext(): Context = app

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()
}