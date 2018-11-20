package me.aluceps.repofinder.ui

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import me.aluceps.repofinder.data.db.entity.RepositoriesEntity
import me.aluceps.repofinder.data.db.entity.mapper.toModel
import me.aluceps.repofinder.data.repository.GithubRepository
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainPresenter @Inject
constructor(
        private val repository: GithubRepository,
        private val customTabsIntent: CustomTabsIntent,
        private val preferences: SharedPreferences
) : MainContract.Presenter<MainContract.View> {

    private lateinit var view: MainContract.View

    private var disposable: CompositeDisposable? = null

    val queryPublisher = PublishSubject.create<String>()

    override fun attachView(view: MainContract.View) {
        this.view = view
    }

    override fun initializePublisher() {
        queryPublisher.distinctUntilChanged()
                .debounce(DELAY_TIME, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<String> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: String) {
                        view.search(t)
                    }

                    override fun onComplete() {
                    }

                    override fun onError(e: Throwable) {
                        Timber.e(e)
                    }
                })
    }

    override fun search(query: String, limit: Int) {
        repository.repositories(query, FIRST_PAGE, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<RepositoriesEntity> {
                    override fun onSubscribe(d: Disposable) {
                        view.showProgressBar()
                        disposable?.clear()
                        disposable = CompositeDisposable(d)
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
                        e.message?.let { view.showSnackbar(it, preferences) }
                    }
                })
    }

    override fun launchUrl(context: Context, url: String) {
        try {
            customTabsIntent.launchUrl(context, Uri.parse(url))
        } catch (e: NullPointerException) {
            Timber.e(e)
        }
    }

    override fun destroy() {
        disposable?.clear()
    }

    companion object {

        private const val FIRST_PAGE = 1

        private const val DELAY_TIME = 500L
    }
}