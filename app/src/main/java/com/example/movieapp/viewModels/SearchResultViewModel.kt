package com.example.movieapp.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecases.GetMovie
import com.example.movieapp.ui.uiState.MovieUIState
import com.example.network.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchResultViewModel @Inject constructor(
    private val getMovie: GetMovie
): ViewModel() {
    private var page = 1
    var movieUIState by mutableStateOf(MovieUIState.Loading as MovieUIState)
        private set

    fun getMovies(queryParameters: List<Pair<String, String>>) {
        if (movieUIState is MovieUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMovie.getByFilter(queryParameters)

            res.onSuccess {
                movieUIState = MovieUIState.Success(it.docs)
            }.onError {

            }
        }
    }

    fun loadMoreMovies(queryParameters: List<Pair<String, String>>) {
        page++

        viewModelScope.launch(Dispatchers.IO) {
            val newQuery = mutableListOf<Pair<String, String>>()
            newQuery.addAll(queryParameters)
            newQuery.add(Constants.PAGE_FIELD to page.toString())

            val res = getMovie.getByFilter(newQuery)

            res.onSuccess {
                val newList = (movieUIState as MovieUIState.Success).data.toMutableList()
                newList.addAll(it.docs)
                movieUIState = MovieUIState.Success(newList)
            }.onError {

            }
        }
    }
}