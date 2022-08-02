package com.makeevrserg.feature_recycler_view.adapter.easy_adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.makeevrserg.feature_recycler_view.adapter.header_adapter.AdapterItem
import com.makeevrserg.feature_recycler_view.adapter.header_adapter.HeaderAdapter


abstract class IEasyAdapter<T>(
    val DIFF_CALLBACK: DiffUtil.ItemCallback<T>,
    vararg viewHolders: BasicViewHolder<*, T, *>
) : ListAdapter<T, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    private val viewHolders = viewHolders.toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder = viewHolders.firstOrNull() {
            it.viewType == viewType
        }?.createViewHolder(parent)
        return viewHolder ?: throw ClassCastException("Unknown viewType ${viewType}")
    }


    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)!!
        return viewHolders.firstOrNull {
            it.clazz.name == item::class.java.name
        }?.viewType ?: throw ClassCastException("Unknown viewType")
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position) as T
        (holder as UniqueViewHolder<T>).bind(item)
    }

}