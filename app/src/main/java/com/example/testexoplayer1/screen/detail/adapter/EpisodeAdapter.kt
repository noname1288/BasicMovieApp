package com.example.testexoplayer1.screen.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testexoplayer1.data.model.EpisodeModel
import com.example.testexoplayer1.databinding.ViewholderEpisodeItemBinding

class EpisodeAdapter(
    private val items: List<EpisodeModel>,
    private val onClicked: (String) -> Unit
) : RecyclerView.Adapter<EpisodeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ViewholderEpisodeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(val binding: ViewholderEpisodeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EpisodeModel) {
            binding.tvEpisodeName.text = item.name
            binding.root.setOnClickListener {
                onClicked(item.linkM3u8)
            }
        }
    }
}
