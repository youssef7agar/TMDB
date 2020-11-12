package com.example.tmdb.details

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.tmdb.R
import com.example.tmdb.common.Constants
import com.example.tmdb.db.entities.Movie
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.item_movie.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsActivity : AppCompatActivity() {

    private val viewModel: MovieDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val dataViews = arrayListOf<View>(
            IMG_movie_details,
            TV_movie_adult_details,
            TV_movie_language_details,
            TV_movie_description_details,
            TV_movie_rating_details,
            TV_movie_release_details,
            TV_movie_name_details,
            TV_movie_genres_details,
            TV_movie_adult_headline_details,
            TV_movie_genres_headline_details,
            TV_movie_language_headline_details
        )

        val movieId = intent.getIntExtra("movieId", 0)
        viewModel.loadMovie(movieId)

        viewModel.viewState.observe(this, Observer {
            it.let { movieViewState ->
                when {
                    movieViewState.loading -> {
                        progress_bar.visibility = View.VISIBLE
                        for (i in dataViews) {
                            i.visibility = View.INVISIBLE
                        }
                    }
                    movieViewState.error != null -> {
                        progress_bar.visibility = View.GONE
                        for (i in dataViews) {
                            i.visibility = View.INVISIBLE
                        }
                        Toast.makeText(
                            this,
                            "Something wrong happened",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    else -> {
                        progress_bar.visibility = View.GONE
                        for (i in dataViews) {
                            i.visibility = View.VISIBLE
                        }
                    }
                }
            }
        })

        viewModel.getMovie(movieId).observe(this, Observer {movieWithGenre ->
            val movie = movieWithGenre.movie
            val genres = movieWithGenre.genres
            TV_movie_name_details.text = movie.title
            TV_movie_release_details.text = movie.releaseDate
            TV_movie_rating_details.text =
                "(" + movie.voteAverage.toString() + ")"
            TV_movie_description_details.text = "\"" + movie.overview + "\""
            TV_movie_language_details.text = movie.originalLanguage
            if (movie.isAdult) {
                TV_movie_adult_details.text = getString(R.string.true_text)
            } else {
                TV_movie_adult_details.text = getString(R.string.false_text)
            }

            TV_movie_genres_details.text = genres.joinToString { it.name }

            Glide.with(this)
                .load(Constants.MAIN_IMAGE_BASE_URL + movie.backdropPath)
                .placeholder(R.drawable.ic_placeholder_foreground)
                .into(IMG_movie_details)

            toolbar.title = movie.title
        })

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
    companion object {
        fun start(context: Activity, movie: Movie, view: View){
            val intent = Intent(context, MovieDetailsActivity::class.java)
            val options = ActivityOptions
                .makeSceneTransitionAnimation(context, view.item_movie_name, "movie_title")
            intent.putExtra("movieId", movie.movieId)
            context.startActivity(intent, options.toBundle())
        }
    }
}