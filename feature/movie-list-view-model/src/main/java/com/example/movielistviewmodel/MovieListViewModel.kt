package com.example.movielistviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.utils.Constants
import com.example.movieScreen.GetMovieByFilter
import com.example.ui.uiState.MovieUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val getMovieByFilter: GetMovieByFilter
): ViewModel() {
    private var page = 1

    private val _movieUIState = MutableStateFlow(MovieUIState.Loading as MovieUIState)
    val moviesState: StateFlow<MovieUIState> = _movieUIState

    fun getMovies(queryParameters: List<Pair<String, String>>) {
        if (moviesState.value is MovieUIState.Success) return

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
            val query = queryParameters.toMutableList()
            query.add(Constants.PAGE_FIELD to page.toString())

            val res = getMovieByFilter.execute(query)

            res.onSuccess {
                val temp = (moviesState.value as MovieUIState.Success)
                    .data
                    .toMutableList()
                temp.addAll(it)
                _movieUIState.value = MovieUIState.Success(temp)
            }
        }
    }
}