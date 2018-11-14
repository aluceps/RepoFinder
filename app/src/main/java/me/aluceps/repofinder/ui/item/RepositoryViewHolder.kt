package me.aluceps.repofinder.ui.item

import android.support.v7.widget.RecyclerView
import android.view.View
import me.aluceps.repofinder.databinding.ViewRepositoryCellBinding

class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding = ViewRepositoryCellBinding.bind(itemView)

    fun initialize() {
        binding.executePendingBindings()
    }
}