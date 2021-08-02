package com.johnnyzhou.movieme.ui.person.list;

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
import com.johnnyzhou.movieme.ui.common.BaseFragment;
import com.johnnyzhou.movieme.ui.person.Person;
import com.johnnyzhou.movieme.ui.person.detail.PersonDetailActivity;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class PersonListFragment extends BaseFragment implements PersonListContract.View {
    private static final String KEY_LAST_QUERY = "last_query";

//    @Bind(R.id.movieRecyclerView)
    RecyclerView recyclerView;
//    @Bind(R.id.movieProgressBar)
    ProgressBar progressBar;
//    @Bind(R.id.errorTextView)
    TextView errorTextView;

    private List<Person> people;
    private String currentQuery;

    public static PersonListFragment newInstance() {
        return new PersonListFragment();
    }

    public PersonListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialiseInjectors();
    }

    private void initialiseInjectors() {
//        DaggerFragmentComponent.builder()
//                .appComponent(getAppComponent())
//                .build()
//                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupRecyclerView();

        if (savedInstanceState != null)
            currentQuery = savedInstanceState.getString(KEY_LAST_QUERY);
    }

    private void setupRecyclerView() {
        List<Person> emptyList = Collections.emptyList();
        PersonListAdapter dummyAdapter = new PersonListAdapter(getContext(), emptyList);
        recyclerView.setAdapter(dummyAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

//    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onPersonClick(PersonListClickEvent personClick) {
        Intent intent = new Intent(getContext(), PersonDetailActivity.class);
        intent.putExtra(PersonDetailActivity.EXTRA_PERSON_ID, people.get(personClick.getPosition()).getId());
        startActivity(intent);
    }

//    @Subscribe
    public void showPopularPeople(ShowPopularPeople showPopularPeople) {
        currentQuery = null;
//        presenter.getPopularPeople();
    }

//    @Subscribe
    public void personSearch(PersonSearch personSearch) {
        currentQuery = personSearch.getQuery();
//        presenter.searchPeople(personSearch.getQuery());
    }

    @Override
    @UiThread
    public void showPeople(List<Person> people) {
        this.people = people;
        recyclerView.swapAdapter(new PersonListAdapter(getContext(), people), true);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    @UiThread
    public void showLoading(boolean showLoading) {
        if (showLoading) recyclerView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(showLoading ? View.VISIBLE : View.GONE);
    }

    @Override
    @UiThread
    public void showError(String message) {
        if (message != null) recyclerView.setVisibility(View.INVISIBLE);
        errorTextView.setVisibility(message == null ? View.INVISIBLE : View.VISIBLE);
        errorTextView.setText(message);
    }

    @Override
    public void onStart() {
        super.onStart();
//        bus.register(this);
//        presenter.bindView(this);

//        if (currentQuery != null) {
//            presenter.searchPeople(currentQuery);
//        } else {
//            presenter.getPopularPeople();
//        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_LAST_QUERY, currentQuery);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
//        presenter.unbindView();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        presenter.onDestroy();
//        presenter = null;
    }
}