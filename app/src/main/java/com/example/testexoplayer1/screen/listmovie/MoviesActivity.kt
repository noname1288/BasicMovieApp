package com.example.testexoplayer1.screen.listmovie

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testexoplayer1.R
import com.example.testexoplayer1.data.model.MovieModel
import com.example.testexoplayer1.data.repository.MovieRepository
import com.example.testexoplayer1.databinding.ActivityMoviesBinding
import com.example.testexoplayer1.screen.listmovie.adapter.MoviesAdapter

class MoviesActivity : AppCompatActivity(), MovieContract.View {
    private lateinit var binding: ActivityMoviesBinding
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var presenter : MoviesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_movies)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    private fun initData(){
        val movieRepository = MovieRepository.instance

        presenter = MoviesPresenter(movieRepository)
        presenter.setView(this)
        presenter.getListMovie()
    }

    override fun showLoading() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    override fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }

    override fun fetchMovies(data: List<MovieModel>) {
        moviesAdapter = MoviesAdapter(data)
        binding.rvMovies.layoutManager = GridLayoutManager(this, 2)
        binding.rvMovies.adapter = moviesAdapter
    }

    override fun showError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
