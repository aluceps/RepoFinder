package me.aluceps.repofinder.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.aluceps.repofinder.R
import me.aluceps.repofinder.model.Repository
import me.aluceps.repofinder.ui.item.EmptyViewHolder
import me.aluceps.repofinder.ui.item.HeaderViewHolder
import me.aluceps.repofinder.ui.item.RepositoryViewHolder

class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: MutableList<Item> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            Type.Empty.id -> EmptyViewHolder(layoutInflater.inflate(R.layout.view_empty_cell, parent, false))
            Type.Header.id -> HeaderViewHolder(layoutInflater.inflate(R.layout.view_header_cell, parent, false))
            else -> RepositoryViewHolder(layoutInflater.inflate(R.layout.view_repository_cell, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (item.type) {
            Type.Empty -> Unit
            Type.Repository -> (holder as? RepositoryViewHolder)?.run {
                (item.value as? Repository)?.let { model ->
                    initialize(model)
                    itemView.setOnClickListener { listener?.click(model.url) }
                }
            }
            Type.Header -> (holder as? HeaderViewHolder)?.run {
                (item.value as? Long)?.let { number ->
                    initialize(number)
                }
            }
            else -> Unit
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = when (items[position].type) {
        Type.Empty -> Type.Empty.id
        Type.Repository -> Type.Repository.id
        Type.Header -> Type.Header.id
        else -> Type.Other.id
    }

    fun addEmpty() {
        items.add(Item(Type.Empty, null))
        notifyDataSetChanged()
    }

    fun addRepository(model: Repository) {
        val position = items.size
        items.add(Item(Type.Repository, model))
        notifyItemInserted(position)
    }

    fun addHeader(number: Long) {
        items.add(0, Item(Type.Header, number))
        notifyItemInserted(0)
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    private enum class Type(val id: Int) {
        Empty(0),
        Repository(1),
        Header(2),
        Other(99),
    }

    private data class Item(
            val type: Type,
            val value: Any?
    )

    interface OnClickListener {
        fun click(url: String)
    }

    private var listener: OnClickListener? = null

    fun setOnClickListener(listener: OnClickListener) {
        this.listener = listener
    }
}