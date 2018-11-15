package me.aluceps.repofinder.data.api.response.mapper

import me.aluceps.repofinder.data.api.response.Repositories
import me.aluceps.repofinder.data.db.entity.RepositoriesEntity

fun Repositories.toEntity(): RepositoriesEntity = this.run {
    RepositoriesEntity(
            count,
            incomplete,
            items.map { it.toEntity() }
    )
}