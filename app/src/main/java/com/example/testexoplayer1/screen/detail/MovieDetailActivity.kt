package com.example.testexoplayer1.screen.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.testexoplayer1.R
import com.example.testexoplayer1.data.model.EpisodeWrapper
import com.example.testexoplayer1.data.model.MovieModel
import com.example.testexoplayer1.data.repository.MovieRepository
import com.example.testexoplayer1.databinding.ActivityMovieDetailBinding
import com.example.testexoplayer1.screen.detail.adapter.EpisodeAdapter
import com.example.testexoplayer1.screen.detail.adapter.ServerAdapter
import com.example.testexoplayer1.screen.listmovie.MoviesActivity

class MovieDetailActivity : AppCompatActivity(), MovieDetailContract.View {
    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var presenter: MovieDetailContract.Presenter
    private lateinit var serverAdapter : ServerAdapter
    private lateinit var episodeAdapter: EpisodeAdapter
    private var slug: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        slug = intent.getStringExtra(MoviesActivity.SLUG) ?: ""
        binding.tvDetailTitle.text = slug

        initView()
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    private fun initData() {
        val movieRepository = MovieRepository.instance
        presenter = MovieDetailPresenter(movieRepository)
        presenter.setView(this)

        if (slug.isNullOrEmpty()){
            showError("Slug is invalid")
            return
        }
        presenter.fetchDataBySlug(slug)
    }

    private fun initView(){
        with(binding){
            btnBack.setOnClickListener {
                finish()
            }
        }
    }

    override fun showLoading() {
        binding.progressBar2.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressBar2.visibility = View.INVISIBLE
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showMovieDetail(
        movieDetail: MovieModel,
        episodeWrapper: List<EpisodeWrapper>
    ) {
        with(binding) {
            Glide.with(this@MovieDetailActivity)
                .load(movieDetail.thumbUrl.ifBlank { movieDetail.posterUrl })
                .error(R.drawable.ic_launcher_background)
                .into(imgPoster)

            tvDetailName.text = movieDetail.name
            tvDescription.text = movieDetail.content

            // --- Tạo adapter server ---
            serverAdapter = ServerAdapter(episodeWrapper) { episodes ->
                // Khi user click server => update danh sách tập
                episodeAdapter = EpisodeAdapter(episodes) { episodeUrl ->
                    Toast.makeText(
                        this@MovieDetailActivity,
                        "Clicked: ${episodeUrl}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                rvListEpisode.adapter = episodeAdapter
            }

            rvListServer.layoutManager = LinearLayoutManager(
                this@MovieDetailActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            rvListServer.adapter = serverAdapter

            rvListEpisode.layoutManager = GridLayoutManager(this@MovieDetailActivity, 3)

            if (episodeWrapper.isNotEmpty()) {
                val firstServer = episodeWrapper[0]
                episodeAdapter = EpisodeAdapter(firstServer.serverData) { episode ->
                    Toast.makeText(
                        this@MovieDetailActivity,
                        "Clicked: ${episode}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                rvListEpisode.adapter = episodeAdapter
            }
        }
    }
}