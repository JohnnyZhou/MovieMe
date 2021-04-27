package com.johnnyzhou.movieme.ui.movie.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.johnnyzhou.movieme.R;
import com.johnnyzhou.movieme.ui.movie.Movie;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private List<Movie> movieList;
    private Context context;

    @Inject
    public MovieListAdapter(Context context, List<Movie> movieList) {
        this.movieList = movieList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieListAdapter.ViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        Glide.with(context)
                .load(movie.getPosterUrl())
                .into(holder.posterImageView);

        holder.titleTextView.setText(movie.getTitle());
        holder.yearTextView.setText(movie.getReleaseDate());
        holder.summaryTextView.setText(movie.getSummary());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.posterImageView)
        ImageView posterImageView;
        @Bind(R.id.movieTitleTextView)
        TextView titleTextView;
        @Bind(R.id.yearTextView)
        TextView yearTextView;
        @Bind(R.id.summaryTextView)
        TextView summaryTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            EventBus.getDefault().post(new MovieListClickEvent(getAdapterPosition()));
        }
    }
}