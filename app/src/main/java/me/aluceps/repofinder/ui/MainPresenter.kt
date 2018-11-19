package me.aluceps.repofinder.ui

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import me.aluceps.repofinder.data.db.entity.RepositoriesEntity
import me.aluceps.repofinder.data.db.entity.mapper.toModel
import me.aluceps.repofinder.data.repository.GithubRepository
import javax.inject.Inject

class MainPresenter @Inject
constructor(
        private val repository: GithubRepository,
        private val customTabsIntent: CustomTabsIntent,
        private val preferences: SharedPreferences
) : MainContract.Presenter<MainContract.View> {

    private lateinit var view: MainContract.View

    private var hasNext = true

    private var currentPage = FIRST_PAGE

    private var disposable: CompositeDisposable? = null

    override fun attachView(view: MainContract.View) {
        this.view = view
    }

    override fun search(q: String, limit: Int) {
        disposable = CompositeDisposable()
        repository.repositories(q, currentPage, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<RepositoriesEntity> {
                    override fun onSubscribe(d: Disposable) {
                        view.showProgressBar()
                        disposable?.add(d)
                    }

                    override fun onSuccess(t: RepositoriesEntity) {
                        view.hideProgressBar()
                        view.clear()
                        when (t.items.isEmpty()) {
                            true -> view.setEmpty()
                            else -> {
                                view.setHeader(t.count)
                                t.items
                                        .map { it.toModel() }
                                        .forEach {
                                            view.setItem(it)
                                        }
                            }
                        }
                    }

                    override fun onError(e: Throwable) {
                        view.hideProgressBar()
                        e.message?.let { view.snackbar(it, preferences) }
                    }
                })
    }

    override fun launchUrl(context: Context, url: String) {
        try {
            customTabsIntent.launchUrl(context, Uri.parse(url))
        } catch (e: NullPointerException) {
        }
    }

    override fun destroy() {
        disposable?.clear()
    }

    companion object {
        private const val FIRST_PAGE = 1
    }
}