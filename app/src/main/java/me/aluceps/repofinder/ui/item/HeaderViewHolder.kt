package me.aluceps.repofinder.ui.item

import android.support.v7.widget.RecyclerView
import android.view.View
import me.aluceps.repofinder.databinding.ViewHeaderCellBinding

class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ViewHeaderCellBinding.bind(itemView)

    fun initialize(number: Long) {
        binding.number = number
        binding.executePendingBindings()
    }
}