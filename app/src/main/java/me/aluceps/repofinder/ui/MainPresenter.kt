package me.aluceps.repofinder.ui

import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import me.aluceps.repofinder.data.db.entity.RepositoriesEntity
import me.aluceps.repofinder.data.repository.GithubRepository
import javax.inject.Inject

class MainPresenter @Inject
constructor(
        private val repository: GithubRepository,
        private val disposable: CompositeDisposable
) : MainContract.Presenter<MainContract.View> {

    private lateinit var view: MainContract.View

    private var hasNext = true

    private var currentPage = FIRST_PAGE

    override fun attachView(view: MainContract.View) {
        this.view = view
    }

    override fun search(q: String, limit: Int) {
        repository.repositories(q, currentPage, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<RepositoriesEntity> {
                    override fun onSubscribe(d: Disposable) {
                        view.showProgressBar()
                        disposable.add(d)
                    }

                    override fun onSuccess(t: RepositoriesEntity) {
                        view.hideProgressBar()
                        hasNext = t.count < currentPage * limit
                        if (hasNext) currentPage++
                    }

                    override fun onError(e: Throwable) {
                        view.hideProgressBar()
                    }
                })
    }

    override fun destroy() {
        disposable.clear()
    }

    companion object {
        private const val FIRST_PAGE = 1
    }
}