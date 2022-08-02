package com.makeevrserg.feature_recycler_view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.makeevrserg.feature_recycler_view.adapter.header_adapter.AdapterItem
import com.makeevrserg.feature_recycler_view.databinding.RecyclerItemBinding

abstract class AbstractAdapter<T>(DIFF_CALLBACK: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, RecyclerView.ViewHolder>(DIFF_CALLBACK) {


}

data class BasicViewHolder<B : ViewBinding, T, V : T>(
    val inflater: (LayoutInflater, ViewGroup, Boolean) -> B,
    val bind: (B, V) -> Unit,
    val clazz: Class<V>,
    val id: Int
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
    val id: Int
    fun bind(item: T)
}

fun <B : ViewBinding, T, V : T> createBinding(
    parent: ViewGroup,
    viewHolderInfo: BasicViewHolder<B, T, V>
): RecyclerView.ViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val inflater = viewHolderInfo.inflater(layoutInflater, parent, false)
    return object : RecyclerView.ViewHolder(inflater.root), UniqueViewHolder<V> {
        override val id: Int = viewHolderInfo.id
        override fun bind(item: V) = viewHolderInfo.bind(inflater, item)
    }
}

