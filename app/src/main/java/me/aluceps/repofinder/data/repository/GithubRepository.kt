package me.aluceps.repofinder.data.repository

import io.reactivex.Single
import me.aluceps.repofinder.data.api.GithubService
import me.aluceps.repofinder.data.api.response.mapper.toEntity
import me.aluceps.repofinder.data.db.entity.RepositoriesEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepository @Inject
constructor(private val service: GithubService) : GithubRemoteDataSource {

    override fun repositories(q: String, page: Int, limit: Int): Single<RepositoriesEntity> =
            service.repositories(q, page, limit).map { it.toEntity() }
}