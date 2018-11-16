package me.aluceps.repofinder.ui

import android.content.Context
import me.aluceps.repofinder.model.Repository

class MainContract {

    interface View {

        fun initializePresenter()

        fun initializeView()

        fun setEmpty()

        fun setItem(model: Repository)

        fun clear()

        fun search(q: String)

        fun showProgressBar()

        fun hideProgressBar()

        fun snackbar(message: String)
    }

    interface Presenter<T : View> {

        fun attachView(view: T)

        fun search(q: String, limit: Int)

        fun launchUrl(context: Context, url: String)

        fun destroy()
    }
}