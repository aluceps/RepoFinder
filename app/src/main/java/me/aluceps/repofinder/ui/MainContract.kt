package me.aluceps.repofinder.ui

import android.content.Context
import android.content.SharedPreferences
import me.aluceps.repofinder.model.Repository

class MainContract {

    interface View {

        fun initializePresenter()

        fun initializeView()

        fun setEmpty()

        fun setItem(model: Repository)

        fun setHeader(number: Long)

        fun clear()

        fun search(query: String)

        fun showProgressBar()

        fun hideProgressBar()

        fun showSnackbar(message: String, preferences: SharedPreferences)
    }

    interface Presenter<T : View> {

        fun attachView(view: T)

        fun initializePublisher()

        fun search(query: String, limit: Int)

        fun launchUrl(context: Context, url: String)

        fun destroy()
    }
}