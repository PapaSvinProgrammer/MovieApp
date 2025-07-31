package com.example.search.searchResult

import androidx.lifecycle.ViewModel
import com.example.movieScreen.GetMovieByFilter
import com.example.ui.uiState.MovieUIState
import com.example.utils.Constants
import com.example.utils.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

internal class SearchResultViewModel @Inject constructor(
    private val getMovieByFilter: GetMovieByFilter
) : ViewModel() {
    private var page = 1

    private val _movieUIState = MutableStateFlow(MovieUIState.Loading as MovieUIState)
    val movieUIState: StateFlow<MovieUIState> = _movieUIState

    fun getMovies(queryParameters: List<Pair<String, String>>) = launchWithoutOld(GET_MOVIES_JOB) {
        if (movieUIState.value is MovieUIState.Success) return@launchWithoutOld

        val res = getMovieByFilter.execute(queryParameters)

        res.onSuccess {
            _movieUIState.value = MovieUIState.Success(it)
        }
    }

    fun loadMoreMovies(queryParameters: List<Pair<String, String>>) = launchWithoutOld(LOAD_MOVIES_JOB) {
        page++

        val newQuery = mutableListOf<Pair<String, String>>()
        newQuery.addAll(queryParameters)
        newQuery.add(Constants.PAGE_FIELD to page.toString())

        val res = getMovieByFilter.execute(newQuery)

        res.onSuccess {
            val newList = (movieUIState.value as MovieUIState.Success).data.toMutableList()
            newList.addAll(it)
            _movieUIState.value = MovieUIState.Success(newList)
        }
    }

    private companion object {
        const val GET_MOVIES_JOB = "get_movies"
        const val LOAD_MOVIES_JOB = "load_movies"
    }
}
