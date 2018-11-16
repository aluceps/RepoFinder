package me.aluceps.repofinder.data.db.entity.mapper

import me.aluceps.repofinder.data.db.entity.RepositoryEntity
import me.aluceps.repofinder.model.Repository

fun RepositoryEntity.toModel(): Repository = this.run {
    Repository(id, name, url)
}