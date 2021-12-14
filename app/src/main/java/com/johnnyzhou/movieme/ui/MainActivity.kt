package com.johnnyzhou.movieme.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.MenuItemCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.johnnyzhou.movieme.R
import com.johnnyzhou.movieme.databinding.ActivityMainBinding
import com.johnnyzhou.movieme.ui.common.BaseActivity
import com.johnnyzhou.movieme.ui.drawer.DrawerItemClick
import com.johnnyzhou.movieme.ui.person.list.PersonListFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    //    lateinit var movieListFragment: MovieListFragment
    lateinit var personListFragment: PersonListFragment
    private var currentQuery: String? = null
    private var searchItem: MenuItem? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContent {
//            MessageCard("hello")
//        }

        binding.hello.text = "hlks"
//        setSupportActionBar(toolbar);
//        val actionBar = supportActionBar
//        actionBar?.setDisplayShowTitleEnabled(false)
//        if (savedInstanceState != null) {
//            currentFragment = savedInstanceState.getInt(KEY_CURRENT_FRAGMENT)
//            currentQuery = savedInstanceState.getString(KEY_LAST_QUERY)
//        }
//
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        val navController = navHostFragment.navController
//
//        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
//        binding.bottomNavigation.setOnNavigationItemReselectedListener {
//            // do nothing
//        }
//
//        initialiseInjectors()
//        setupDrawer()
//        setupFragment()
        }

//    @Preview
//    @Composable
//    fun MessageCard(name: String) {
//        Text(text = name)
//    }

    private fun initialiseInjectors() {
//        DaggerActivityComponent.builder()
//            .appComponent(applicationComponent)
//            .activityModule(ActivityModule(this))
//            .build()
//            .inject(this)
    }

    private fun setupFragment() {
//        movieListFragment = MovieListFragment.newInstance()
//        if (currentFragment == MOVIES_FRAGMENT) {
//            // TODO set toolbar title
//            if (supportFragmentManager.findFragmentByTag(TAG_MOVIE_LIST) == null) {
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.contentContainer, movieListFragment, TAG_MOVIE_LIST)
//                    .commit()
//            }
//        } else if (currentFragment == PEOPLE_FRAGMENT) {
//            // TODO set toolbar title
//            if (supportFragmentManager.findFragmentByTag(TAG_PERSON_LIST) == null) {
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.contentContainer, personListFragment, TAG_PERSON_LIST)
//                    .commit()
//            }
//        }
    }

    private fun setupDrawer() {
//        val titles = arrayOf("Movies", "People")
//        val icons = intArrayOf(R.drawable.ic_movie, R.drawable.ic_people)
//        val drawerAdapter: RecyclerView.Adapter<*> = DrawerAdapter(titles, icons)
//        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
//        with(binding) {
//            drawerRecycler.setHasFixedSize(true)
//            drawerRecycler.adapter = drawerAdapter
//            drawerRecycler.layoutManager = layoutManager
//        }
//        val drawerToggle = ActionBarDrawerToggle(
//            this,
//            binding.drawerLayout,
//            binding.toolbar.root,
//            R.string.openDrawer,
//            R.string.closeDrawer
//        )
//        binding.drawerLayout.setDrawerListener(drawerToggle)
//        drawerToggle.syncState()
    }

    //    @Subscribe(threadMode = ThreadMode.MainThread)
    fun onDrawerItemClick(clickEvent: DrawerItemClick) {
//        binding.drawerLayout.closeDrawer(binding.drawerRecycler)
        if (clickEvent.position == DrawerItemClick.MOVIE_POSITION) {
            if (currentFragment != MOVIES_FRAGMENT) resetSearchView()
            currentFragment = MOVIES_FRAGMENT
            setupFragment()
        } else if (clickEvent.position == DrawerItemClick.PEOPLE_POSITION) {
            if (currentFragment != PEOPLE_FRAGMENT) resetSearchView()
            currentFragment = PEOPLE_FRAGMENT
            setupFragment()
        }
    }

    private fun resetSearchView() {
//        MenuItemCompat.collapseActionView(searchItem);
        currentQuery = null
    }

    override fun onBackPressed() {
//        val drawer = findViewById<View>(R.id.drawerLayout) as DrawerLayout
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START)
//        } else {
        super.onBackPressed()
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        searchItem = menu.findItem(R.id.action_search)
        // TODO, safe
//        val searchView = searchItem!!.actionView as SearchView
//        if (currentQuery != null) {
//            MenuItemCompat.expandActionView(searchItem)
//            searchView.setQuery(currentQuery, false)
//            searchView.clearFocus()
//            closeSoftKeyboard()
//        }
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String): Boolean {
//                if (currentFragment == MOVIES_FRAGMENT) {
//                } else if (currentFragment == PEOPLE_FRAGMENT) {
//                }
//                //                    bus.post(new PersonSearch(query));
//                closeSoftKeyboard()
//                currentQuery = query
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
//                return false
//            }
//        })
        MenuItemCompat.setOnActionExpandListener(
            searchItem,
            object : MenuItemCompat.OnActionExpandListener {
                override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                    return true
                }

                override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                    currentQuery = null
                    showDefaultItems()
                    return true
                }

                private fun showDefaultItems() {
//                bus.post(currentFragment == MOVIES_FRAGMENT ?
//                        new ShowPopularMovie() : new ShowPopularPeople());
                }
            })
        return true
    }

    private fun closeSoftKeyboard() {
        val view = currentFocus
        if (view != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_CURRENT_FRAGMENT, currentFragment)
        outState.putString(KEY_LAST_QUERY, currentQuery)
        super.onSaveInstanceState(outState)
    }

    companion object {
        private const val MOVIES_FRAGMENT = 0
        private const val PEOPLE_FRAGMENT = 1
        private const val TAG_MOVIE_LIST = "movie_list"
        private const val TAG_PERSON_LIST = "person_list"
        private const val KEY_CURRENT_FRAGMENT = "current_fragment"
        private const val KEY_LAST_QUERY = "last_query"
        private var currentFragment = MOVIES_FRAGMENT
    }
}