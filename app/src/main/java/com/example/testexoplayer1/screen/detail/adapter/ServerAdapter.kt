package com.example.testexoplayer1.screen.detail.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testexoplayer1.data.model.EpisodeModel
import com.example.testexoplayer1.data.model.EpisodeWrapper
import com.example.testexoplayer1.databinding.ViewholderEpisodeItemBinding

class ServerAdapter(
    private val episodeWrapper: List<EpisodeWrapper>,
    private val onClick: (List<EpisodeModel>) -> Unit
) : RecyclerView.Adapter<ServerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            ViewholderEpisodeItemBinding.inflate(
                android.view.LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(episodeWrapper[position])
    }

    override fun getItemCount(): Int = episodeWrapper.size

    inner class ViewHolder(val binding: ViewholderEpisodeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EpisodeWrapper) {
            binding.tvEpisodeName.text = item.serverName // Name of server: Vietsub/ Thuyet minh/...
            binding.root.setOnClickListener {
                onClick(item.serverData)
            }
        }
    }
}