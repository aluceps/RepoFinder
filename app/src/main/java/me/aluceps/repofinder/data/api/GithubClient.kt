package me.aluceps.repofinder.data.api

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubClient @Inject constructor(private val service: GithubService) {

    fun repositories(q: String, page: Int, limit: Int) {
        service.repositories(q, page, limit)
    }
}