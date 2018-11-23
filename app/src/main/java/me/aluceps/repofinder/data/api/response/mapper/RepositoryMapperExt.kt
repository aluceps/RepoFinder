package me.aluceps.repofinder.data.api.response.mapper

import me.aluceps.repofinder.data.api.response.Repository
import me.aluceps.repofinder.data.db.entity.RepositoryEntity

fun Repository.toEntity(): RepositoryEntity = this.run {
    RepositoryEntity(
            id,
            name,
            url,
            owner.avater,
            description
    )
}
