package me.aluceps.repofinder.data.db.entity

data class RepositoryEntity(
        val id: Long,
        val name: String,
        val url: String,
        val avater: String,
        val description: String?
)