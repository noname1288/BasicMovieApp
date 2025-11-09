package com.example.testexoplayer1.screen.listmovie.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testexoplayer1.R
import com.example.testexoplayer1.data.model.MovieModel
import com.example.testexoplayer1.databinding.ViewholderMovieItemBinding

class MoviesAdapter (private val movies: List<MovieModel>) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {
    private val TAG = "MoviesAdapter"

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        Log.d(TAG, "onCreateViewHolder: called")
        val binding = ViewholderMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int
    ) {
        Log.d(TAG, "onBindViewHolder: called for position $position")
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    inner class MovieViewHolder(val binding: ViewholderMovieItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: MovieModel)= with(binding){
            val context = root.context

            Glide.with(context)
                .load(item.thumb_url)
                .error(R.drawable.ic_launcher_background)
                .into(imgThumbnail)

            tvCurrentEpisode.text = item.episode_current
            tvTimeRemaining.text = item.time
            tvName.text = item.name
            tvYear.text = item.year.toString()
        }
    }


}