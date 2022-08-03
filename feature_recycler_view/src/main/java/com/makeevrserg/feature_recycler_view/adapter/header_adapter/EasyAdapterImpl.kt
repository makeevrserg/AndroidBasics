package com.makeevrserg.feature_recycler_view.adapter.header_adapter

import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.makeevrserg.feature_recycler_view.adapter.easy_adapter.BasicViewHolder
import com.makeevrserg.feature_recycler_view.adapter.easy_adapter.IEasyAdapter
import com.makeevrserg.feature_recycler_view.databinding.RecyclerHeaderBinding
import com.makeevrserg.feature_recycler_view.databinding.RecyclerItemBinding

class EasyAdapterImpl : IEasyAdapter<AdapterItem>(DIFF_CALLBACK, CharacterBinding(), HeaderBinding()) {
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

private class CharacterBinding: BasicViewHolder<RecyclerItemBinding, AdapterItem, AdapterItem.CharacterItem>(RecyclerItemBinding::inflate){

    override val bind = { binding: RecyclerItemBinding, item: AdapterItem.CharacterItem ->
        binding.tvIndex.text = item.index.toString()
        val character = item.character

        Glide.with(binding.root.context).load(character.image).into(binding.ivImage)
        binding.tvFirstSeen.text = character.origin.name
        binding.tvName.text = character.name
        binding.tvLocation.text = character.location.name
    }
    override val clazz = AdapterItem.CharacterItem::class.java
    override val viewType = 0
}

private class HeaderBinding: BasicViewHolder<RecyclerHeaderBinding, AdapterItem, AdapterItem.Header>(RecyclerHeaderBinding::inflate){

    override val bind = { binding: RecyclerHeaderBinding, item: AdapterItem.Header ->
        binding.tvHeader.text = item.text
    }
    override val clazz = AdapterItem.Header::class.java
    override val viewType = 1
}

