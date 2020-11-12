package com.example.tmdb.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdb.R
import com.example.tmdb.common.Constants
import com.example.tmdb.db.entities.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesListAdapter(private val clickListener: (movie: Movie, view: View) -> Unit) :
    ListAdapter<Movie, MoviesListAdapter.ViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie, clickListener: (movie: Movie, view: View) -> Unit) {
            itemView.item_movie_name.text = movie.title ?: ""
            itemView.item_release_date.text = movie.releaseDate ?: ""
            Glide.with(itemView.context)
                .load(Constants.MAIN_IMAGE_BASE_URL + movie.posterPath)
                .placeholder(R.drawable.ic_placeholder_foreground)
                .into(itemView.item_movie_image)
            itemView.setOnClickListener {
                clickListener(movie, it)
            }
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.movieId == newItem.movieId
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}