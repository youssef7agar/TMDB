package com.example.tmdb.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.tmdb.R
import com.example.tmdb.common.BaseActivity
import com.example.tmdb.details.MovieDetailsActivity
import kotlinx.android.synthetic.main.activity_movies_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception

class MoviesListActivity : BaseActivity() {

    private val viewModel: MoviesViewModel by viewModel()
    private var loadingMore = false
    override fun onNoConnection() {
        viewModel.cancelJobs()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)

        refreshLayout.setOnRefreshListener {
            viewModel.loadMoviesList()
        }

        val adapter = MoviesListAdapter { movie, itemView ->
            MovieDetailsActivity.start(this, movie, itemView)
        }
        RV_movies.adapter = adapter
        viewModel.viewState.observe(this, Observer {
            it.let { moviesListViewState ->
                when {
                    moviesListViewState.loading -> {
                        RV_movies.visibility = View.INVISIBLE
                        progress_bar.visibility = View.VISIBLE
                        refreshLayout.isEnabled = false
                    }
                    moviesListViewState.error != null -> {
                        RV_movies.visibility = View.VISIBLE
                        progress_bar.visibility = View.GONE
                        refreshLayout.isEnabled = true
                        Toast.makeText(
                            this,
                            "Something wrong happened",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    moviesListViewState.loadingMore -> {
                        pbMore.visibility = View.VISIBLE
                        progress_bar.visibility = View.GONE
                        refreshLayout.isEnabled = false
                    }
                    else -> {
                        RV_movies.visibility = View.VISIBLE
                        progress_bar.visibility = View.GONE
                        pbMore.visibility = View.GONE
                        refreshLayout.isEnabled = true
                        refreshLayout.isRefreshing = false
                        loadingMore = false

                    }
                }
            }
        })

        viewModel.movies().observe(this, Observer {
            adapter.submitList(it)
        })
        scrollView.viewTreeObserver.addOnScrollChangedListener {
            try {
                val diff = RV_movies.bottom - (scrollView.height + scrollView.scrollY)
                if (diff == 0 && RV_movies.bottom != 0 && !loadingMore) {
                    viewModel.nextMoviesList()
                    loadingMore = true
                }
            } catch (e: Exception) {

            }
        }
    }
}