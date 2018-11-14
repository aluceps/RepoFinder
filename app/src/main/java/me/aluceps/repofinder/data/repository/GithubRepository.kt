package me.aluceps.repofinder.data.repository

import me.aluceps.repofinder.data.api.GithubClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepository @Inject
constructor(private val client: GithubClient) : GithubRemoteDataSource {
    override fun repositories(q: String, page: Int, limit: Int) {
        client.repositories(q, page, limit)
    }
}