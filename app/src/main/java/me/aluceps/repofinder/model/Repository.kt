package me.aluceps.repofinder.model

data class Repository(
        val id: Long,
        val name: String,
        val url: String,
        val avater: String,
        val description: String?
)