package com.makeevrserg.feature_recycler_view.adapter.header_adapter

import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.makeevrserg.feature_recycler_view.adapter.BasicViewHolder
import com.makeevrserg.feature_recycler_view.databinding.RecyclerHeaderBinding
import com.makeevrserg.feature_recycler_view.databinding.RecyclerItemBinding

class EasyAdapterImpl(
    viewHolders: List<BasicViewHolder<*, AdapterItem, *>> = listOf(
        BasicViewHolder(
            inflater = RecyclerItemBinding::inflate,
            bind = { binding: RecyclerItemBinding, item: AdapterItem.CharacterItem ->
                val item = item.character
                Glide.with(binding.root.context).load(item.image).into(binding.ivImage)
                binding.tvFirstSeen.text = item.origin.name
                binding.tvName.text = item.name
                binding.tvLocation.text = item.location.name
            },
            clazz = AdapterItem.CharacterItem::class.java,
            id = 0
        ),
        BasicViewHolder(
            inflater = RecyclerHeaderBinding::inflate,
            bind = { binding: RecyclerHeaderBinding, item: AdapterItem.Header ->
                binding.tvHeader.text = item.text
            },
            clazz = AdapterItem.Header::class.java,
            id = 1
        ),
    )
) : HeaderEasyAdapter<AdapterItem>(DIFF_CALLBACK, viewHolders) {
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