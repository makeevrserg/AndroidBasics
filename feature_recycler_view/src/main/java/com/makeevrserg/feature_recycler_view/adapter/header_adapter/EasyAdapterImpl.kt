package com.makeevrserg.feature_recycler_view.adapter.header_adapter

import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.makeevrserg.feature_recycler_view.adapter.easy_adapter.BasicViewHolder
import com.makeevrserg.feature_recycler_view.adapter.easy_adapter.IEasyAdapter
import com.makeevrserg.feature_recycler_view.databinding.RecyclerHeaderBinding
import com.makeevrserg.feature_recycler_view.databinding.RecyclerItemBinding

class EasyAdapterImpl : IEasyAdapter<AdapterItem>(DIFF_CALLBACK, CharacterBinding, HeaderBinding) {
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
}

private val CharacterBinding: BasicViewHolder<RecyclerItemBinding, AdapterItem, AdapterItem.CharacterItem> =
    BasicViewHolder(
        inflater = RecyclerItemBinding::inflate,
        bind = { binding: RecyclerItemBinding, item: AdapterItem.CharacterItem ->
            binding.tvIndex.text = item.index.toString()
            val item = item.character
            Glide.with(binding.root.context).load(item.image).into(binding.ivImage)
            binding.tvFirstSeen.text = item.origin.name
            binding.tvName.text = item.name
            binding.tvLocation.text = item.location.name
        },
        clazz = AdapterItem.CharacterItem::class.java,
        viewType = 0
    )
private val HeaderBinding: BasicViewHolder<RecyclerHeaderBinding, AdapterItem, AdapterItem.Header> =
    BasicViewHolder(
        inflater = RecyclerHeaderBinding::inflate,
        bind = { binding: RecyclerHeaderBinding, item: AdapterItem.Header ->
            binding.tvHeader.text = item.text
        },
        clazz = AdapterItem.Header::class.java,
        viewType = 1
    )