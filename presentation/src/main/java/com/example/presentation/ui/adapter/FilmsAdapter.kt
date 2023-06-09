package com.example.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.presentation.databinding.ItemFilmsBinding
import com.example.presentation.models.FilmsResponseUI

class FilmsAdapter : ListAdapter<FilmsResponseUI, FilmsAdapter.FilmsViewHolder>(diffUtil) {
    inner class FilmsViewHolder(private val binding: ItemFilmsBinding) : ViewHolder(binding.root) {
        fun onBind(item: FilmsResponseUI?) {
            binding.textTitle.text = item?.title
            binding.textOriginal.text = item?.originalTitle
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        return FilmsViewHolder(
            ItemFilmsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<FilmsResponseUI>() {
            override fun areItemsTheSame(oldItem: FilmsResponseUI, newItem: FilmsResponseUI): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: FilmsResponseUI,
                newItem: FilmsResponseUI
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}