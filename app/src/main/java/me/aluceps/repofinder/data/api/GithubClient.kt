package me.aluceps.repofinder.data.api

import io.reactivex.Single
import me.aluceps.repofinder.data.api.response.Repositories
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubClient @Inject constructor(private val service: GithubService) {

    fun repositories(q: String, page: Int, limit: Int): Single<Repositories> =
        service.repositories(q, page, limit)
}