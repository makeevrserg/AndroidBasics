package com.makeevrserg.feature_recycler_view.adapter.header_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makeevrserg.feature_recycler_view.adapter.AbstractAdapter
import com.makeevrserg.feature_recycler_view.adapter.BasicViewHolder
import com.makeevrserg.feature_recycler_view.adapter.UniqueViewHolder
import com.makeevrserg.feature_recycler_view.databinding.RecyclerHeaderBinding
import com.makeevrserg.feature_recycler_view.databinding.RecyclerItemBinding


abstract class HeaderEasyAdapter<T>(
    val DIFF_CALLBACK: DiffUtil.ItemCallback<T>,
    val viewHolders: List<BasicViewHolder<*, T, *>>
) :
    AbstractAdapter<T>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder = viewHolders.firstOrNull() {
            it.id == viewType
        }?.createViewHolder(parent)
        return viewHolder ?: throw ClassCastException("Unknown viewType ${viewType}")
    }


    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)!!
        return viewHolders.firstOrNull { it.clazz == item::class.java }?.id
            ?: throw ClassCastException("Unknown viewType")
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val id = (holder as? UniqueViewHolder<*>)?.id
        val item = getItem(position) as AdapterItem
        val binder =
            viewHolders.firstOrNull { it.id == id } ?: throw ClassCastException("Unknown id ${id}")
        (binder.viewHolder as UniqueViewHolder<AdapterItem>).bind(item)
    }

}