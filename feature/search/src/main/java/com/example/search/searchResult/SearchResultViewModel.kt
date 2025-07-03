package com.example.search.searchResult

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.Constants
import com.example.movieScreen.GetMovieByFilter
import com.example.ui.uiState.MovieUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchResultViewModel @Inject constructor(
    private val getMovieByFilter: GetMovieByFilter
): ViewModel() {
    private var page = 1
    var movieUIState by mutableStateOf(MovieUIState.Loading as MovieUIState)
        private set

    fun getMovies(queryParameters: List<Pair<String, String>>) {
        if (movieUIState is MovieUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMovieByFilter.execute(queryParameters)

            res.onSuccess {
                movieUIState = MovieUIState.Success(it)
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
                val newList = (movieUIState as MovieUIState.Success).data.toMutableList()
                newList.addAll(it)
                movieUIState = MovieUIState.Success(newList)
            }
        }
    }
}