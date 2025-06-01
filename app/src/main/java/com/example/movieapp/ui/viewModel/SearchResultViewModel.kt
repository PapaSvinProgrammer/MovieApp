package com.example.movieapp.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecases.GetMovie
import com.example.movieapp.ui.screen.uiState.MovieUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchResultViewModel @Inject constructor(
    private val getMovie: GetMovie
): ViewModel() {
    var movieUIState by mutableStateOf(MovieUIState.Loading as MovieUIState)
        private set

    fun getMovies(queryParameters: Map<String, String>) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = getMovie.getByFilter(queryParameters)

            if (res.isNotEmpty()) {
                movieUIState = MovieUIState.Success(res)
            }
        }
    }
}