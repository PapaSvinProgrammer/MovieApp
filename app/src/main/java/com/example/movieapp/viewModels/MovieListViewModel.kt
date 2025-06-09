package com.example.movieapp.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecases.GetMovie
import com.example.movieapp.ui.screen.uiState.MovieUIState
import com.example.network.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val getMovie: GetMovie
): ViewModel() {
    private var page = 1
    var moviesState by mutableStateOf(MovieUIState.Loading as MovieUIState)
        private set

    fun getMovies(queryParameters: List<Pair<String, String>>) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = getMovie.getByFilter(queryParameters)

            if (res.isNotEmpty()) {
                moviesState = MovieUIState.Success(res)
            }
        }
    }

    fun loadMoreMovies(queryParameters: List<Pair<String, String>>) {
        page++

        viewModelScope.launch(Dispatchers.IO) {
            val query = mutableListOf<Pair<String, String>>()
            query.addAll(queryParameters)
            query.add(Constants.PAGE_FIELD to page.toString())

            val res = getMovie.getByFilter(query)

            val temp = (moviesState as MovieUIState.Success).data.toMutableList()
            temp.addAll(res)
            moviesState = MovieUIState.Success(temp)
        }
    }
}