package me.aluceps.repofinder.data.api.response

import com.google.gson.annotations.SerializedName

data class Repository(
        val id: Long,
        @SerializedName("full_name")
        val name: String,
        @SerializedName("html_url")
        val url: String,
        val owner: Owner,
        val description: String?
) {
    data class Owner(
            @SerializedName("avatar_url")
            val avater: String
    )
}