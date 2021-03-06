package me.aluceps.repofinder.ui.item

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import me.aluceps.repofinder.databinding.ViewRepositoryCellBinding
import me.aluceps.repofinder.model.Repository
import me.aluceps.repofinder.viewmodel.RepositoryViewModel

class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ViewRepositoryCellBinding.bind(itemView)

    fun initialize(model: Repository) {
        binding.model = RepositoryViewModel.create(model)
        binding.executePendingBindings()
    }
}