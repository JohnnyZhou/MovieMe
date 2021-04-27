package com.johnnyzhou.movieme.ui.movie.detail;


import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.UiThread;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.palette.graphics.Palette;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.johnnyzhou.movieme.R;
import com.johnnyzhou.movieme.di.component.DaggerFragmentComponent;
import com.johnnyzhou.movieme.ui.common.BaseActivity;
import com.johnnyzhou.movieme.ui.common.BaseFragment;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MovieDetailFragment extends BaseFragment implements MovieDetailContract.View {

    private static final String ARG_MOVIE_ID = "movie_id";

    @Bind(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.runningTimeTextView)
    TextView runningTimeTextView;
    @Bind(R.id.movieDetailProgressBar)
    ProgressBar progressBar;
    @Bind(R.id.backdropImageView)
    ImageView backdropImageView;
    @Bind(R.id.taglineSection)
    RelativeLayout taglineSection;
    @Bind(R.id.releasedTextView)
    TextView releasedTextView;
    @Bind(R.id.languageTextView)
    TextView languageTextView;
    @Bind(R.id.overviewTextView)
    TextView overviewTextView;
    @Bind(R.id.posterImageView)
    ImageView posterImageView;
    @Bind(R.id.taglineTextView)
    TextView taglineTextView;
    @Bind(R.id.detailCardView)
    CardView detailCardView;
    @Bind(R.id.genresTextView)
    TextView genresTextView;
    @Bind(R.id.budgetTextView)
    TextView budgetTextView;

    @Inject
    MovieDetailContract.Presenter<MovieDetailContract.View> presenter;

    private String movieId;

    public static MovieDetailFragment newInstance(String movieId) {
        Bundle args = new Bundle();
        args.putString(ARG_MOVIE_ID, movieId);

        MovieDetailFragment fragment = new MovieDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public MovieDetailFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieId = getArguments().getString(ARG_MOVIE_ID);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_movie_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
        setupToolbar();
    }

    private void setupToolbar() {
        ((BaseActivity) getActivity()).setSupportActionBar(toolbar);
        BaseActivity activity = (BaseActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        ActionBar actionBar = activity.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    @UiThread
    public void showLoading(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        detailCardView.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
        posterImageView.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
        taglineSection.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
        backdropImageView.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public void showError(String errorMessage) {
        if (errorMessage != null) detailCardView.setVisibility(View.INVISIBLE);
        Timber.wtf(errorMessage);
        // TODO
    }

    @Override
    @UiThread
    public void showMovie(MovieDetail movie) {
        progressBar.setVisibility(View.INVISIBLE);
        detailCardView.setVisibility(View.VISIBLE);
        posterImageView.setVisibility(View.VISIBLE);
        taglineSection.setVisibility(View.VISIBLE);
        backdropImageView.setVisibility(View.VISIBLE);

        overviewTextView.setText(movie.getSummary());
        taglineTextView.setText(movie.getTagline());
        collapsingToolbar.setTitle(movie.getTitle());

        releasedTextView.setText(movie.getReleaseDate());
        runningTimeTextView.setText(movie.getRuntime());
        genresTextView.setText(movie.getGenres());
        budgetTextView.setText(movie.getBudget());
        languageTextView.setText(movie.getLanguages());
        detailCardView.setVisibility(View.VISIBLE);

        Target target = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(final Bitmap resource, GlideAnimation glideAnimation) {
                backdropImageView.setImageBitmap(resource);
                Palette.from(resource).generate(new Palette.PaletteAsyncListener() {
                    public void onGenerated(Palette palette) {
                        int black = ContextCompat.getColor(getContext(), R.color.black);
                        int white = ContextCompat.getColor(getContext(), R.color.white);
                        collapsingToolbar.setExpandedTitleColor(palette.getLightVibrantColor(white));
                        collapsingToolbar.setCollapsedTitleTextColor(palette.getLightVibrantColor(white));
                        taglineSection.setBackgroundColor(palette.getMutedColor(white));
                        taglineTextView.setTextColor(palette.getLightVibrantColor(white));
                        changeStatusWindowColor(palette, black);
                    }

                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    private void changeStatusWindowColor(Palette palette, int defaultColor) {
                        Window window = getActivity().getWindow();
                        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.setStatusBarColor(palette.getDarkVibrantColor(defaultColor));
                    }
                });
            }
        };

        Glide.with(getContext())
                .load(movie.getBackdropUrl())
                .asBitmap()
                .into(target);

        Glide.with(getContext())
                .load(movie.getPosterUrl())
                .into(posterImageView);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.bindView(this);
        presenter.getMovie(movieId);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        presenter.unbindView();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        presenter = null;
    }
}