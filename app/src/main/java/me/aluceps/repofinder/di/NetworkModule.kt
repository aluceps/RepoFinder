package me.aluceps.repofinder.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import me.aluceps.repofinder.BuildConfig
import me.aluceps.repofinder.data.api.GithubService
import me.aluceps.repofinder.data.api.RequestInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRequestInterceptor(preferences: SharedPreferences): Interceptor = RequestInterceptor(preferences)

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(interceptor)
        if (BuildConfig.DEBUG) addNetworkInterceptor(
                HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)
        )
    }.build()

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .build()

    @Singleton
    @Provides
    fun provideGithubService(retrofit: Retrofit): GithubService = retrofit.create(GithubService::class.java)

    companion object {
        private const val BASE_URL = "https://api.github.com"
    }
}