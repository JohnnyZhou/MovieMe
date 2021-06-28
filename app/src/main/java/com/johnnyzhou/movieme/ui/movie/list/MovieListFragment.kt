package com.johnnyzhou.movieme.ui.movie.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.UiThread
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import com.johnnyzhou.movieme.databinding.FragmentMovieListBinding
import com.johnnyzhou.movieme.di.component.DaggerFragmentComponent
import com.johnnyzhou.movieme.ui.common.BaseFragment
import com.johnnyzhou.movieme.ui.movie.Movie
import com.johnnyzhou.movieme.ui.movie.detail.MovieDetailActivity
import de.greenrobot.event.Subscribe
import de.greenrobot.event.ThreadMode

private const val KEY_LAST_QUERY = "last_query"

class MovieListFragment : BaseFragment(), MovieListContract.View {
    private val binding by lazy { FragmentMovieListBinding.inflate(layoutInflater) }
    private val viewModel: MovieListViewModel by viewModels()

    private var movies: List<Movie>? = null
    private var currentQuery: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        retainInstance = true
        initialiseDependencies()
    }

    private fun initialiseDependencies() {
        DaggerFragmentComponent.builder()
            .appComponent(appComponent)
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this, view)
        setupRecyclerView()
        if (savedInstanceState != null) currentQuery = savedInstanceState.getString(KEY_LAST_QUERY)

        viewModel.getMovieList()
    }

    private fun setupRecyclerView() {
        val emptyList: List<Movie> = emptyList()
        val emptyAdapter = MovieListAdapter(context, emptyList)
        binding.movieRecyclerView.adapter = emptyAdapter
        binding.movieRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    fun onMovieClick(movieClick: MovieListClickEvent) {
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.EXTRA_MOVIE_ID, movies!![movieClick.position].id)
        startActivity(intent)
    }

    fun movieSearch(movieSearch: MovieSearch) {
        currentQuery = movieSearch.query
        viewModel.searchMovie(movieSearch.query)
    }

    fun showPopularMovie(showPopularMovie: ShowPopularMovie?) {
        currentQuery = null
        viewModel.getPopularMovies()
    }

    @UiThread
    override fun showMovies(movies: List<Movie>) {
        this.movies = movies
        binding.movieRecyclerView.swapAdapter(MovieListAdapter(context, movies), true)
        binding.movieRecyclerView.visibility = View.VISIBLE
    }

    override fun showLoading(show: Boolean) {
        binding.movieRecyclerView.visibility = View.INVISIBLE
        binding.movieRecyclerView.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showError(message: String) {
        binding.errorTextView.visibility = View.VISIBLE
        binding.errorTextView.text = message
    }

    override fun onStart() {
        super.onStart()
        if (currentQuery != null) {
            viewModel.searchMovie(currentQuery)
        } else {
            viewModel.getPopularMovies()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(KEY_LAST_QUERY, currentQuery)
        super.onSaveInstanceState(outState)
    }

    companion object {
        fun newInstance(): MovieListFragment {
            return MovieListFragment()
        }
    }
}