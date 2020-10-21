package com.example.restproject.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.restproject.network.Property
import com.example.restproject.databinding.ListItemViewBinding

class MainAdapter() :
    ListAdapter<Property, com.example.restproject.fragment.MainAdapter.PropertyViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): com.example.restproject.fragment.MainAdapter.PropertyViewHolder {
        return PropertyViewHolder(ListItemViewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: com.example.restproject.fragment.MainAdapter.PropertyViewHolder, position: Int) {
        val marsProperty = getItem(position)
        holder.bind(marsProperty)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Property>() {
        override fun areItemsTheSame(oldItem: Property, newItem: Property): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Property, newItem: Property): Boolean {
            return oldItem.name == newItem.name
        }
    }

    class PropertyViewHolder(private var binding: ListItemViewBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(property: Property) {
            binding.property = property
            binding.executePendingBindings()
        }

    }

}



