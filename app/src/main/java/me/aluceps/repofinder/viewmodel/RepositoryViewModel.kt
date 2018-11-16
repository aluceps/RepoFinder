package me.aluceps.repofinder.viewmodel

import android.databinding.BaseObservable
import android.databinding.ObservableField
import me.aluceps.repofinder.model.Repository

class RepositoryViewModel : BaseObservable() {

    val name: ObservableField<String> = ObservableField()

    val url: ObservableField<String> = ObservableField()

    init {
        name.set("")
        url.set("")
    }

    private fun set(model: Repository) {
        name.set(model.name)
        url.set(model.url)
    }

    companion object {
        fun create(model: Repository): RepositoryViewModel = RepositoryViewModel().apply {
            set(model)
        }
    }
}