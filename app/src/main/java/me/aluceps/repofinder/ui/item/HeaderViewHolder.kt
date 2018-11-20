package me.aluceps.repofinder.ui.item

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import me.aluceps.repofinder.databinding.ViewHeaderCellBinding

class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ViewHeaderCellBinding.bind(itemView)

    fun initialize(number: Long) {
        binding.number = number
        binding.executePendingBindings()
    }
}