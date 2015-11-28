package com.johnnyzhou.movieme.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.johnnyzhou.movieme.R;
import com.johnnyzhou.movieme.di.component.DaggerActivityComponent;
import com.johnnyzhou.movieme.di.module.ActivityModule;
import com.johnnyzhou.movieme.ui.base.BaseActivity;
import com.johnnyzhou.movieme.ui.drawer.DrawerAdapter;
import com.johnnyzhou.movieme.ui.drawer.DrawerItemClick;
import com.johnnyzhou.movieme.ui.movie.list.MovieListFragment;
import com.johnnyzhou.movieme.ui.movie.list.MovieSearch;
import com.johnnyzhou.movieme.ui.movie.list.ShowPopularMovie;
import com.johnnyzhou.movieme.ui.person.list.PersonListFragment;
import com.johnnyzhou.movieme.ui.person.list.PersonSearch;
import com.johnnyzhou.movieme.ui.person.list.ShowPopularPeople;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class MainActivity extends BaseActivity {
    private static final int MOVIES_FRAGMENT = 0;
    private static final int PEOPLE_FRAGMENT = 1;
    private static final String TAG_MOVIE_LIST = "movie_list";
    private static final String TAG_PERSON_LIST = "person_list";
    private static final String KEY_CURRENT_FRAGMENT = "current_fragment";
    private static final String KEY_LAST_QUERY = "last_query";
    private static int currentFragment = MOVIES_FRAGMENT;

    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.drawerRecycler)
    RecyclerView recyclerView;
    @Inject
    MovieListFragment movieListFragment;
    @Inject
    PersonListFragment personListFragment;
    @Inject
    FragmentManager fm;
    @Inject
    EventBus bus;

    private String currentQuery;
    private MenuItem searchItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayShowTitleEnabled(false);

        if (savedInstanceState != null) {
            currentFragment = savedInstanceState.getInt(KEY_CURRENT_FRAGMENT);
            currentQuery = savedInstanceState.getString(KEY_LAST_QUERY);
        }

        initialiseInjectors();

        setupDrawer();
        setupFragment();
    }

    private void initialiseInjectors() {
        DaggerActivityComponent.builder()
                .appComponent(getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build()
                .inject(this);
    }

    private void setupFragment() {
        if (currentFragment == MOVIES_FRAGMENT) {
            toolbar.setTitle("Movies");
            if (fm.findFragmentByTag(TAG_MOVIE_LIST) == null) {
                fm.beginTransaction()
                        .replace(R.id.contentContainer, movieListFragment, TAG_MOVIE_LIST)
                        .commit();
            }
        } else if (currentFragment == PEOPLE_FRAGMENT) {
            toolbar.setTitle("People");
            if (fm.findFragmentByTag(TAG_PERSON_LIST) == null) {
                fm.beginTransaction()
                        .replace(R.id.contentContainer, personListFragment, TAG_PERSON_LIST)
                        .commit();
            }
        }
    }

    private void setupDrawer() {
        String titles[] = {"Movies", "People"};
        int icons[] = {R.drawable.ic_movie, R.drawable.ic_people};

        RecyclerView.Adapter drawerAdapter = new DrawerAdapter(titles, icons);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(drawerAdapter);
        recyclerView.setLayoutManager(layoutManager);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onDrawerItemClick(DrawerItemClick clickEvent) {
        if (drawerLayout != null)
            drawerLayout.closeDrawer(recyclerView);

        if (clickEvent.getPosition() == DrawerItemClick.MOVIE_POSITION) {
            if (currentFragment != MOVIES_FRAGMENT)
                resetSearchView();

            currentFragment = MOVIES_FRAGMENT;
            setupFragment();
        } else if (clickEvent.getPosition() == DrawerItemClick.PEOPLE_POSITION) {
            if (currentFragment != PEOPLE_FRAGMENT)
                resetSearchView();

            currentFragment = PEOPLE_FRAGMENT;
            setupFragment();
        }
    }

    private void resetSearchView() {
        MenuItemCompat.collapseActionView(searchItem);
        currentQuery = null;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();

        if (currentQuery != null) {
            MenuItemCompat.expandActionView(searchItem);
            searchView.setQuery(currentQuery, false);
            searchView.clearFocus();
            closeSoftKeyboard();
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (currentFragment == MOVIES_FRAGMENT)
                    bus.post(new MovieSearch(query));
                else if (currentFragment == PEOPLE_FRAGMENT)
                    bus.post(new PersonSearch(query));

                closeSoftKeyboard();
                currentQuery = query;
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                currentQuery = null;
                showDefaultItems();
                return true;
            }

            private void showDefaultItems() {
                bus.post(currentFragment == MOVIES_FRAGMENT ?
                        new ShowPopularMovie() : new ShowPopularPeople());
            }
        });

        return true;
    }

    private void closeSoftKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(KEY_CURRENT_FRAGMENT, currentFragment);
        outState.putString(KEY_LAST_QUERY, currentQuery);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        bus.register(this);
    }

    @Override
    protected void onStop() {
        bus.unregister(this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}