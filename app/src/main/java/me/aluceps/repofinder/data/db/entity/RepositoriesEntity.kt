package me.aluceps.repofinder.data.db.entity

data class RepositoriesEntity(
        val count: Long,
        val incomplete: Boolean,
        val items: List<RepositoryEntity>
)