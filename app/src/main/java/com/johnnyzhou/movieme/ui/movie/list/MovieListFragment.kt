package com.johnnyzhou.movieme.ui.movie.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.johnnyzhou.movieme.databinding.FragmentMovieListBinding
import com.johnnyzhou.movieme.di.component.DaggerFragmentComponent
import com.johnnyzhou.movieme.ui.common.BaseFragment
import com.johnnyzhou.movieme.ui.common.UiState
import com.johnnyzhou.movieme.ui.movie.Movie
import com.johnnyzhou.movieme.ui.movie.detail.MovieDetailActivity
import dagger.Lazy
import javax.inject.Inject

private const val KEY_LAST_QUERY = "last_query"

class MovieListFragment : BaseFragment() {
    private val binding by lazy { FragmentMovieListBinding.inflate(layoutInflater) }

//    private lateinit var viewModel: MovieListViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MovieListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(MovieListViewModel::class.java)
    }

    private var movies: List<Movie>? = null
    private var currentQuery: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerFragmentComponent.builder()
            .appComponent(appComponent)
            .build()
            .inject(this)

        viewModel.uiState.observe(this) {
            setUiState(it)
        }

        viewModel.movieList.observe(this, {
            (binding.movieRecyclerView.adapter as MovieListAdapter).setMovies(it)
        })
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
        setupRecyclerView()

        if (savedInstanceState != null) currentQuery = savedInstanceState.getString(KEY_LAST_QUERY)

        // TODO, how to restore state?
//        viewModel.getMovieList()
    }

    private fun setupRecyclerView() {
        binding.movieRecyclerView.adapter = MovieListAdapter(requireContext())
        binding.movieRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun setUiState(uiState: UiState) {
        arrayListOf<Int>().sort()
        when (uiState) {
            UiState.Error -> showErrorState()
            UiState.Loading -> showLoadingState()
            UiState.Success -> showSuccessState()
        }
    }

    private fun showLoadingState() {
        binding.movieProgressBar.visibility = View.VISIBLE
        binding.errorTextView.visibility = View.GONE
    }

    private fun showErrorState() {
        binding.movieRecyclerView.visibility = View.GONE
        binding.movieProgressBar.visibility = View.GONE
        binding.errorTextView.visibility = View.VISIBLE
        binding.errorTextView.text = "Error"
    }

    private fun showSuccessState() {
        binding.movieRecyclerView.visibility = View.VISIBLE
        binding.movieProgressBar.visibility = View.GONE
        binding.errorTextView.visibility = View.GONE
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
//        viewModel.getPopularMovies()
    }

    fun showLoading(show: Boolean) {
        binding.movieRecyclerView.visibility = View.INVISIBLE
        binding.movieRecyclerView.visibility = if (show) View.VISIBLE else View.GONE
    }

    fun showError(message: String) {
        binding.errorTextView.visibility = View.VISIBLE
        binding.errorTextView.text = message
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.uiState.removeObservers(this)
        viewModel.movieList.removeObservers(this)
    }

    override fun onStart() {
        super.onStart()

        if (currentQuery != null) {
            viewModel.searchMovie(currentQuery)
        } else {
//            viewModel.getPopularMovies()
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