package com.johnnyzhou.movieme.ui.movie.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.UiThread;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.johnnyzhou.movieme.R;
import com.johnnyzhou.movieme.di.component.DaggerFragmentComponent;
import com.johnnyzhou.movieme.ui.common.BaseFragment;
import com.johnnyzhou.movieme.ui.movie.Movie;
import com.johnnyzhou.movieme.ui.movie.detail.MovieDetailActivity;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class MovieListFragment extends BaseFragment implements MovieListContract.View {
    private static final String KEY_LAST_QUERY = "last_query";

    @Bind(R.id.movieRecyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.movieProgressBar)
    ProgressBar progressBar;
    @Bind(R.id.errorTextView)
    TextView errorTextView;

    @Inject
    MovieListContract.Presenter<MovieListContract.View> presenter;
    @Inject
    EventBus bus;

    private List<Movie> movies;
    private String currentQuery;

    public static MovieListFragment newInstance() {
        return new MovieListFragment();
    }

    public MovieListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        initialiseInjectors();
    }

    private void initialiseInjectors() {
        DaggerFragmentComponent.builder()
                .appComponent(getAppComponent())
                .build()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        setupRecyclerView();

        if (savedInstanceState != null)
            currentQuery = savedInstanceState.getString(KEY_LAST_QUERY);
    }

    private void setupRecyclerView() {
        List<Movie> emptyList = Collections.emptyList();
        MovieListAdapter emptyAdapter = new MovieListAdapter(getContext(), emptyList);
        recyclerView.setAdapter(emptyAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onMovieClick(MovieListClickEvent movieClick) {
        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        intent.putExtra(MovieDetailActivity.EXTRA_MOVIE_ID, movies.get(movieClick.getPosition()).getId());
        startActivity(intent);
    }

    @Subscribe
    public void movieSearch(MovieSearch movieSearch) {
        currentQuery = movieSearch.getQuery();
        presenter.searchMovie(movieSearch.getQuery());
    }

    @Subscribe
    public void showPopularMovie(ShowPopularMovie showPopularMovie) {
        currentQuery = null;
        presenter.getPopularMovies();
    }

    @Override
    @UiThread
    public void showMovies(List<Movie> movies) {
        this.movies = movies;
        recyclerView.swapAdapter(new MovieListAdapter(getContext(), movies), true);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    @UiThread
    public void showLoading(boolean show) {
        recyclerView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    @UiThread
    public void showError(String message) {
        errorTextView.setVisibility(message == null ? View.INVISIBLE : View.VISIBLE);
        errorTextView.setText(message);
    }

    @Override
    public void onStart() {
        super.onStart();
        bus.register(this);
        presenter.bindView(this);

        if (currentQuery != null) {
            presenter.searchMovie(currentQuery);
        } else {
            presenter.getPopularMovies();
        }
    }

    @Override
    public void onStop() {
        bus.unregister(this);
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        presenter.unbindView();
        super.onDestroyView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_LAST_QUERY, currentQuery);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}