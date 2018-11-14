package me.aluceps.repofinder.data.repository

interface GithubRemoteDataSource {
    fun repositories(q: String, page: Int, limit: Int)
}