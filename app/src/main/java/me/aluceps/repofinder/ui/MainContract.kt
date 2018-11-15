package me.aluceps.repofinder.ui

class MainContract {

    interface View {

        fun initializePresenter()

        fun initializeView()

        fun setItem()

        fun search()

        fun showProgressBar()

        fun hideProgressBar()
    }

    interface Presenter<T : View> {

        fun attachView(view: T)

        fun search(q: String, limit: Int)

        fun destroy()
    }
}