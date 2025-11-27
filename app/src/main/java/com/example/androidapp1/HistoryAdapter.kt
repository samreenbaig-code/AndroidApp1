package com.example.androidapp1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidapp1.databinding.ItemHistoryBinding

class HistoryAdapter(
    private val items: List<HistoryItem>,
    private val listener: HistoryListener
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    interface HistoryListener {
        fun onDelete(item: HistoryItem, position: Int)
    }

    inner class HistoryViewHolder(val binding: ItemHistoryBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = items[position]

        // Show value and timestamp
        holder.binding.tvHistoryValue.text = "Count: ${item.value}"
        holder.binding.tvHistoryTime.text = "Saved at: ${item.displayTime}"

        // Delete action
        holder.binding.btnDeleteHistory.setOnClickListener {
            listener.onDelete(item, position)
        }
    }

    override fun getItemCount(): Int = items.size
}
