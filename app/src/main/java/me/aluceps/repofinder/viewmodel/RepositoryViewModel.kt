package me.aluceps.repofinder.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import me.aluceps.repofinder.model.Repository

class RepositoryViewModel : BaseObservable() {

    val name: ObservableField<String> = ObservableField()

    val url: ObservableField<String> = ObservableField()

    val avater: ObservableField<String> = ObservableField()

    init {
        name.set("")
        url.set("")
        avater.set("")
    }

    private fun set(model: Repository) {
        name.set(model.name)
        url.set(model.url)
        avater.set(model.avater)
    }

    companion object {
        fun create(model: Repository): RepositoryViewModel = RepositoryViewModel().apply {
            set(model)
        }
    }
}