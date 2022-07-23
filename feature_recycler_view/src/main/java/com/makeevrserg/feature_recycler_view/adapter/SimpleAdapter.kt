package com.makeevrserg.feature_recycler_view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makeevrserg.domain.rick_and_morty.models.Character
import com.makeevrserg.feature_recycler_view.databinding.RecyclerItemBinding

class SimpleAdapter : ListAdapter<Character, SimpleAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(
                oldItem: Character,
                newItem: Character
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: Character,
                newItem: Character
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding:RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item:Character){
            Glide.with(binding.root.context).load(item.image).into(binding.ivImage)
            binding.tvFirstSeen.text = item.origin.name
            binding.tvName.text = item.name
            binding.tvLocation.text = item.location.name
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}