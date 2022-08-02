package com.makeevrserg.feature_recycler_view.adapter.header_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makeevrserg.domain.rick_and_morty.models.Character
import com.makeevrserg.feature_recycler_view.databinding.RecyclerHeaderBinding
import com.makeevrserg.feature_recycler_view.databinding.RecyclerItemBinding

private val ITEM_VIEW_TYPE_HEADER = 0
private val ITEM_VIEW_TYPE_ITEM = 1

class HeaderAdapter : ListAdapter<AdapterItem, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AdapterItem>() {
            override fun areItemsTheSame(
                oldItem: AdapterItem,
                newItem: AdapterItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: AdapterItem,
                newItem: AdapterItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> HeaderViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> CharacterViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }

    }


    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is AdapterItem.Header -> ITEM_VIEW_TYPE_HEADER
            is AdapterItem.CharacterItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    class CharacterViewHolder(private val binding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): CharacterViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecyclerItemBinding.inflate(layoutInflater, parent, false)
                return CharacterViewHolder(binding)
            }
        }

        fun bind(item: AdapterItem.CharacterItem) {
            binding.tvIndex.text = item.index.toString()
            val item = item.character
            Glide.with(binding.root.context).load(item.image).into(binding.ivImage)
            binding.tvFirstSeen.text = item.origin.name
            binding.tvName.text = item.name
            binding.tvLocation.text = item.location.name
        }
    }

    class HeaderViewHolder(private val binding: RecyclerHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): HeaderViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecyclerHeaderBinding.inflate(layoutInflater, parent, false)
                return HeaderViewHolder(binding)
            }
        }

        fun bind(item: AdapterItem.Header) {
            binding.tvHeader.text = item.text
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CharacterViewHolder -> {
                val item = getItem(position) as AdapterItem.CharacterItem
                holder.bind(item)
            }
            is HeaderViewHolder -> {
                val item = getItem(position) as AdapterItem.Header
                holder.bind(item)
            }
        }
    }
}