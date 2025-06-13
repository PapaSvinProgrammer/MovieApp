package com.example.movieapp.viewModels

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

class MovieViewModel @Inject constructor(
    private val getMovie: GetMovie
): ViewModel() {
    var movieState by mutableStateOf(MovieUIState.Loading as MovieUIState)
        private set

    fun getMovie(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = getMovie.getById(id)

            if (res != null) {
                movieState = MovieUIState.Success(listOf(res))
            }
        }
    }
}