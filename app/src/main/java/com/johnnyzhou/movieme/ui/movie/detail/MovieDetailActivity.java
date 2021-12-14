package com.johnnyzhou.movieme.ui.movie.detail;

import android.os.Bundle;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import com.johnnyzhou.movieme.R;
import com.johnnyzhou.movieme.ui.common.BaseActivity;

public class MovieDetailActivity extends BaseActivity {
    private static final String TAG_MOVIE_DETAIL = "movie_detail";
    public static final String EXTRA_MOVIE_ID = "com.johnnyzhou.movieme.movie_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        initialiseInjections();
        setupFragment();
    }

    private void initialiseInjections() {
//        DaggerActivityComponent.builder()
//                .appComponent(getApplicationComponent())
//                .activityModule(new ActivityModule(this))
//                .build()
//                .inject(this);
    }


    private void setupFragment() {
        if (getSupportFragmentManager().findFragmentByTag(TAG_MOVIE_DETAIL) != null) return;

        String movieId = getIntent().getStringExtra(EXTRA_MOVIE_ID);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.detail_movie_container, MovieDetailFragment.newInstance(movieId), TAG_MOVIE_DETAIL)
                .commit();
    }
}