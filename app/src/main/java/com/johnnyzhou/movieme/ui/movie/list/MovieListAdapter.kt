package com.johnnyzhou.movieme.ui.movie.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.johnnyzhou.movieme.databinding.RowMovieBinding
import com.johnnyzhou.movieme.ui.movie.Movie

class MovieListAdapter(private val context: Context) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private var movieList: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(context, movieList[position])
    }

    override fun getItemCount() = movieList.size

    fun setMovies(movies: List<Movie>) {
        movieList.clear()
        movieList.addAll(movies)
        notifyDataSetChanged()
    }

    class ViewHolder(private val itemBinding: RowMovieBinding) :
        RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
//            EventBus.getDefault().post(MovieListClickEvent(adapterPosition))
        }

        fun onBind(context: Context, movie: Movie) {
            with(itemBinding) {
                Glide.with(context) // inject in
                    .load(movie.posterUrl)
                    .into(posterImageView)

                movieTitleTextView.text = movie.title
                yearTextView.text = movie.releaseDate
                summaryTextView.text = movie.summary
            }
        }
    }
}