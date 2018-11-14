package me.aluceps.repofinder.data.api

import io.reactivex.Single
import me.aluceps.repofinder.data.api.response.Repositories
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {

    @GET("/search/repositories")
    fun repositories(
        @Query("q") q: String,
        @Query("page") page: Int,
        @Query("per_page") limit: Int
    ): Single<Repositories>
}