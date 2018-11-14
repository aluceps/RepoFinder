package me.aluceps.repofinder.data.repository

import io.reactivex.Single
import me.aluceps.repofinder.data.api.GithubClient
import me.aluceps.repofinder.data.api.response.Repositories
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepository @Inject
constructor(private val client: GithubClient) : GithubRemoteDataSource {

    override fun repositories(q: String, page: Int, limit: Int): Single<Repositories> =
        client.repositories(q, page, limit)
}