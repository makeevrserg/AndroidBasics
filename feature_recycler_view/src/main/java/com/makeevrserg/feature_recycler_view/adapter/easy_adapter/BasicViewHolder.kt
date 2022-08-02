package com.makeevrserg.feature_recycler_view.adapter.easy_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

data class BasicViewHolder<B : ViewBinding, T, V : T>(
    val inflater: (LayoutInflater, ViewGroup, Boolean) -> B,
    val bind: (B, V) -> Unit,
    val clazz: Class<V>,
    val viewType: Int
) {
    var viewHolder: RecyclerView.ViewHolder? = null
        private set

    fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val viewHolder = createBinding(parent, this)
        this.viewHolder = viewHolder
        return viewHolder
    }

}

interface UniqueViewHolder<T> {
    val viewType: Int
    fun bind(item: T)
}

private fun <B : ViewBinding, T, V : T> createBinding(
    parent: ViewGroup,
    viewHolderInfo: BasicViewHolder<B, T, V>
): RecyclerView.ViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val inflater = viewHolderInfo.inflater(layoutInflater, parent, false)
    return object : RecyclerView.ViewHolder(inflater.root), UniqueViewHolder<V> {
        override val viewType: Int = viewHolderInfo.viewType
        override fun bind(item: V) = viewHolderInfo.bind(inflater, item)
    }
}

