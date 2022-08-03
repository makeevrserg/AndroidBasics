package com.makeevrserg.feature_recycler_view.adapter.easy_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BasicViewHolder<B : ViewBinding, T, V : T>(val inflater: (LayoutInflater, ViewGroup, Boolean) -> B) {

    abstract val bind: (B, V) -> Unit
    abstract val clazz: Class<V>
    abstract val viewType: Int
    fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = createBinding(parent, this)

}

interface UniqueViewHolder<T> {
    val viewType: Int
    fun bind(item: T)
}

private fun <B : ViewBinding, T, V : T> createBinding(
    parent: ViewGroup, basicViewHolder: BasicViewHolder<B, T, V>
): RecyclerView.ViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val inflater = basicViewHolder.inflater(layoutInflater, parent, false)
    return object : RecyclerView.ViewHolder(inflater.root), UniqueViewHolder<V> {
        override val viewType: Int = basicViewHolder.viewType
        override fun bind(item: V) = basicViewHolder.bind(inflater, item)
    }
}

