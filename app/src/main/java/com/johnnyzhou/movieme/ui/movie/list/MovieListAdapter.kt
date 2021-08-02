package com.johnnyzhou.movieme.ui.movie.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.johnnyzhou.movieme.R
import com.johnnyzhou.movieme.ui.movie.Movie
import java.io.FilterInputStream
import javax.inject.Inject

class MovieListAdapter @Inject constructor(
    private val context: Context,
    private val movieList: List<Movie>
) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.row_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList[position]
        Glide.with(context)
            .load(movie.posterUrl)
            .into(holder.posterImageView)
        holder.titleTextView!!.text = movie.title
        holder.yearTextView!!.text = movie.releaseDate
        holder.summaryTextView!!.text = movie.summary
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
//        @Bind(R.id.posterImageView)
        var posterImageView: ImageView? = null

//        @Bind(R.id.movieTitleTextView)
        var titleTextView: TextView? = null

//        @Bind(R.id.yearTextView)
        var yearTextView: TextView? = null

//        @Bind(R.id.summaryTextView)
        var summaryTextView: TextView? = null

        override fun onClick(v: View) {
//            EventBus.getDefault().post(MovieListClickEvent(adapterPosition))
        }

        init {
            itemView.setOnClickListener(this)
        }
    }
}