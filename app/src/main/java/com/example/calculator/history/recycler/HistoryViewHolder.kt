package com.example.calculator.history.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.databinding.ItemHistoryBinding

class HistoryViewHolder(private val binding: ItemHistoryBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(historyItem: String) {
        binding.resultItem.text = historyItem
    }
}