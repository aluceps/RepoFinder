package me.aluceps.repofinder.ui

import android.content.Context
import javax.inject.Inject

class MainPresenter @Inject
constructor(context: Context) : MainContract.Presenter<MainContract.View> {

    private lateinit var view: MainContract.View

    override fun attachView(view: MainContract.View) {
        this.view = view
    }
}