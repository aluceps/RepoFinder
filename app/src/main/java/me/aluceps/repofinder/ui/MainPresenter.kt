package me.aluceps.repofinder.ui

import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import me.aluceps.repofinder.data.api.response.Repositories
import me.aluceps.repofinder.data.repository.GithubRepository
import javax.inject.Inject

class MainPresenter @Inject
constructor(
    private val repository: GithubRepository,
    private val disposable: CompositeDisposable
) : MainContract.Presenter<MainContract.View> {

    private lateinit var view: MainContract.View

    override fun attachView(view: MainContract.View) {
        this.view = view
    }

    override fun search() {
        repository.repositories("dagger", 1, 20)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Repositories> {
                override fun onSubscribe(d: Disposable) {
                    disposable.add(d)
                }

                override fun onSuccess(t: Repositories) {
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    override fun destroy() {
        disposable.clear()
    }
}