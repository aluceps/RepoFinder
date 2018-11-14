package me.aluceps.repofinder.ui

import me.aluceps.repofinder.databinding.ActivityMainBinding

class MainContract {

    interface View {

        fun initializePresenter()

        fun initializeView(binding: ActivityMainBinding)

        fun setItem()
    }

    interface Presenter<T : View> {

        fun attachView(view: T)

        fun search()

        fun destroy()
    }
}