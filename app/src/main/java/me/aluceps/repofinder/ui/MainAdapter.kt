package me.aluceps.repofinder.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import me.aluceps.repofinder.R
import me.aluceps.repofinder.ui.item.EmptyViewHolder
import me.aluceps.repofinder.ui.item.RepositoryViewHolder

class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: MutableList<Item> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            Type.Empty.id -> EmptyViewHolder(layoutInflater.inflate(R.layout.view_empty_cell, parent, false))
            else -> RepositoryViewHolder(layoutInflater.inflate(R.layout.view_repository_cell, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (item.type) {
            Type.Empty -> Unit
            Type.Repository -> (holder as? RepositoryViewHolder)?.run {
                initialize()
                itemView.setOnClickListener { listener?.click() }
            }
            else -> Unit
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = when (items[position].type) {
        Type.Empty -> Type.Empty.id
        Type.Repository -> Type.Repository.id
        else -> Type.Other.id
    }

    fun addEmpty() {
        items.add(Item(Type.Empty, null))
    }

    fun addRepository(repo: String) {
        items.add(Item(Type.Repository, repo))
    }

    private enum class Type(val id: Int) {
        Empty(0),
        Repository(1),
        Other(99),
    }

    private data class Item(
            val type: Type,
            val value: String?
    )

    interface OnClickListener {
        fun click()
    }

    private var listener: OnClickListener? = null

    fun setOnClickListener(listener: OnClickListener) {
        this.listener = listener
    }
}