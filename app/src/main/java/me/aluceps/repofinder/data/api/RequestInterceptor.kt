package me.aluceps.repofinder.data.api

import android.content.SharedPreferences
import me.aluceps.repofinder.util.ext.getOAuth
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RequestInterceptor @Inject constructor(
        private val preferences: SharedPreferences
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response =
            chain.proceed(chain.request().newBuilder().apply {
                preferences.getOAuth()?.let { addHeader(AUTH_TOKEN, it) }
            }.build())

    companion object {
        private const val AUTH_TOKEN = "Authorization: token"
    }
}