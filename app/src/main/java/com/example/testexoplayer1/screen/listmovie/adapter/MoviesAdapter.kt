package com.example.testexoplayer1.screen.listmovie.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testexoplayer1.R
import com.example.testexoplayer1.data.model.MovieWrapper
import com.example.testexoplayer1.databinding.ViewholderMovieItemBinding

class MoviesAdapter(private val movies: List<MovieWrapper>, private val onClicked: (String) -> Unit) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val binding =
            ViewholderMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int
    ) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    inner class MovieViewHolder(val binding: ViewholderMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieWrapper) = with(binding) {
            val context = root.context

            Glide.with(context)
                .load(item.thumb_url)
                .error(R.drawable.ic_launcher_background)
                .into(imgThumbnail)

            tvCurrentEpisode.text = item.episode_current
            tvTimeRemaining.text = item.time
            tvName.text = item.name
            tvYear.text = item.year.toString()

            root.setOnClickListener {
                onClicked(item.slug)
            }
        }
    }
}
