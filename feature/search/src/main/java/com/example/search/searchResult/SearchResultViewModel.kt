package com.example.search.searchResult

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.Constants
import com.example.movieScreen.GetMovieByFilter
import com.example.ui.uiState.MovieUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchResultViewModel @Inject constructor(
    private val getMovieByFilter: GetMovieByFilter
): ViewModel() {
    private var page = 1
    private val _movieUIState = MutableStateFlow(MovieUIState.Loading as MovieUIState)
    val movieUIState: StateFlow<MovieUIState> = _movieUIState

    fun getMovies(queryParameters: List<Pair<String, String>>) {
        if (movieUIState.value is MovieUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMovieByFilter.execute(queryParameters)

            res.onSuccess {
                _movieUIState.value = MovieUIState.Success(it)
            }
        }
    }

    fun loadMoreMovies(queryParameters: List<Pair<String, String>>) {
        page++

        viewModelScope.launch(Dispatchers.IO) {
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
    }
}