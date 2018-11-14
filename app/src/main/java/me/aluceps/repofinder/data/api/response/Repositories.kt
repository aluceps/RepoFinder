package me.aluceps.repofinder.data.api.response

import com.google.gson.annotations.SerializedName

data class Repositories(
    @SerializedName("total_count")
    val count: Int,
    @SerializedName("incomplete_results")
    val incomplete: Boolean,
    val items: List<Repository>
)