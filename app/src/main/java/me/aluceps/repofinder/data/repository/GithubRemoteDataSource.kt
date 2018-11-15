package me.aluceps.repofinder.data.repository

import io.reactivex.Single
import me.aluceps.repofinder.data.db.entity.RepositoriesEntity

interface GithubRemoteDataSource {
    fun repositories(q: String, page: Int, limit: Int): Single<RepositoriesEntity>
}