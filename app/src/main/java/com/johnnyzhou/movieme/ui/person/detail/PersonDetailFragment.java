package com.johnnyzhou.movieme.ui.person.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.johnnyzhou.movieme.R;
import com.johnnyzhou.movieme.ui.common.BaseActivity;
import com.johnnyzhou.movieme.ui.common.BaseFragment;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PersonDetailFragment extends BaseFragment implements PersonDetailContract.View {
    private static final String ARG_PERSON_ID = "person_id";

//    @Bind(R.id.toolbar)
    Toolbar toolbar;
//    @Bind(R.id.backdropImageView)
    ImageView backdropImageView;
//    @Bind(R.id.movieDetailProgressBar)
    ProgressBar progressBar;
//    @Bind(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbar;
//    @Bind(R.id.overviewTextView)
    TextView overview;
//    @Bind(R.id.errorTextView)
    TextView errorTextView;
//    @Bind(R.id.dobTextView)
    TextView dobTextView;

//    @Inject
    PersonDetailContract.Presenter<PersonDetailContract.View> presenter;

    private String personId;

    public static PersonDetailFragment newInstance(String movieId) {
        Bundle args = new Bundle();
        args.putString(ARG_PERSON_ID, movieId);

        PersonDetailFragment fragment = new PersonDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public PersonDetailFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personId = getArguments().getString(ARG_PERSON_ID);
        setRetainInstance(true);
        initialiseInjectors();
    }

    private void initialiseInjectors() {
//        DaggerFragmentComponent.builder()
//                .appComponent(getAppComponent())
//                .build()
//                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_person_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
    public void showPerson(PersonDetail person) {
        dobTextView.setVisibility(View.VISIBLE);
        overview.setVisibility(View.VISIBLE);
        overview.setText(person.getBiography());
        collapsingToolbar.setTitle(person.getName());
        Glide.with(getContext())
                .load(person.getBackdropUrl())
                .into(backdropImageView);

        if (person.getBirthdayString() == null) {
            dobTextView.setVisibility(View.GONE);
        } else {
            dobTextView.setText(person.getBirthdayString());
        }
    }

    @Override
    public void showLoading(boolean showLoading) {
        if (showLoading) {
            dobTextView.setVisibility(View.INVISIBLE);
            overview.setVisibility(View.INVISIBLE);
        }

        progressBar.setVisibility(showLoading ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void showError(String errorMsg) {
        errorTextView.setVisibility(errorMsg == null ? View.INVISIBLE : View.VISIBLE);
        errorTextView.setText(errorMsg);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.bindView(this);
        presenter.getPerson(personId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        presenter = null;
    }
}