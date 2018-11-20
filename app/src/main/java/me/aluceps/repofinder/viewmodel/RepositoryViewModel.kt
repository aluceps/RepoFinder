package me.aluceps.repofinder.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import me.aluceps.repofinder.model.Repository

class RepositoryViewModel : BaseObservable() {

    val name: ObservableField<String> = ObservableField()

    val avater: ObservableField<String> = ObservableField()

    val description: ObservableField<String> = ObservableField()

    init {
        name.set("")
        avater.set("")
        description.set("")
    }

    private fun set(model: Repository) {
        name.set(model.name)
        avater.set(model.avater)
        description.set(model.description ?: "")
    }

    companion object {
        fun create(model: Repository): RepositoryViewModel = RepositoryViewModel().apply {
            set(model)
        }
    }
}