package com.example.movielistviewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.Constants
import com.example.movieScreen.GetMoviesByFilter
import com.example.ui.uiState.MovieUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val getMoviesByFilter: GetMoviesByFilter
): ViewModel() {
    private var page = 1
    var moviesState by mutableStateOf(MovieUIState.Loading as MovieUIState)
        private set

    fun getMovies(queryParameters: List<Pair<String, String>>) {
        if (moviesState is MovieUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMoviesByFilter.execute(queryParameters)

            res.onSuccess {
                moviesState = MovieUIState.Success(it)
            }
        }
    }

    fun loadMoreMovies(queryParameters: List<Pair<String, String>>) {
        page++

        viewModelScope.launch(Dispatchers.IO) {
            val query = queryParameters.toMutableList()
            query.add(Constants.PAGE_FIELD to page.toString())

            val res = getMoviesByFilter.execute(query)

            res.onSuccess {
                val temp = (moviesState as MovieUIState.Success).data.toMutableList()
                temp.addAll(it)
                moviesState = MovieUIState.Success(temp)
            }
        }
    }
}