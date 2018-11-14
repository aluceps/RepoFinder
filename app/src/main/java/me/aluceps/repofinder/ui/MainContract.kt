package me.aluceps.repofinder.ui

class MainContract {

    interface View {
        fun initializeView()
    }

    interface Presenter<T : View> {
        fun attachView(view: T)
    }
}