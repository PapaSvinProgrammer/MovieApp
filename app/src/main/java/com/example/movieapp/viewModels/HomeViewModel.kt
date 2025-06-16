package com.example.movieapp.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecases.GetCollection
import com.example.core.domain.usecases.GetMovie
import com.example.movieapp.ui.uiState.CollectionUIState
import com.example.movieapp.ui.uiState.MovieUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getMovie: GetMovie,
    private val getCollection: GetCollection
): ViewModel() {
    var movieDramaState by mutableStateOf(MovieUIState.Loading as MovieUIState)
        private set
    var movieBoevikState by mutableStateOf(MovieUIState.Loading as MovieUIState)
        private set
    var movieBest250State by mutableStateOf(MovieUIState.Loading as MovieUIState)
        private set
    var movieBest501State by mutableStateOf(MovieUIState.Loading as MovieUIState)
        private set
    var movieBest100State by mutableStateOf(MovieUIState.Loading as MovieUIState)
        private set
    var movieHBOState by mutableStateOf(MovieUIState.Loading as MovieUIState)
        private set
    var movieNetflixState by mutableStateOf(MovieUIState.Loading as MovieUIState)
        private set
    var collectionState by mutableStateOf(CollectionUIState.Loading as CollectionUIState)
        private set

    fun getMoviesDrama() {
        if (movieDramaState is MovieUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMovie.getMoviesByGenre("драма")

            res.onSuccess {
                movieDramaState = MovieUIState.Success(it.docs)
            }.onError {

            }
        }
    }

    fun getMoviesBoevik() {
        if (movieBoevikState is MovieUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMovie.getMoviesByGenre("боевик")

            res.onSuccess {
                movieBoevikState = MovieUIState.Success(it.docs)
            }.onError {

            }
        }
    }

    fun getMoviesBest250() {
        if (movieBest250State is MovieUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMovie.getMoviesByCollection("top250")

            res.onSuccess {
                movieBest250State = MovieUIState.Success(it.docs)
            }.onError {

            }
        }
    }

    fun getMoviesBest501() {
        if (movieBest501State is MovieUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMovie.getMoviesByCollection("best_501")

            res.onSuccess {
                movieBest501State = MovieUIState.Success(it.docs)
            }.onError {

            }
        }
    }

    fun getMoviesBest100() {
        if (movieBest100State is MovieUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMovie.getMoviesByCollection("top_100_scifi_by_total_scifi_online")

            res.onSuccess {
                movieBest100State = MovieUIState.Success(it.docs)
            }.onError {

            }
        }
    }

    fun getMoviesHBO() {
        if (movieHBOState is MovieUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMovie.getMoviesByCompany("HBO")

            res.onSuccess {
                movieHBOState = MovieUIState.Success(it.docs)
            }.onError {

            }
        }
    }

    fun getMoviesNetflix() {
        if (movieNetflixState is MovieUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMovie.getMoviesByCompany("Netflix")

            res.onSuccess {
                movieNetflixState = MovieUIState.Success(it.docs)
            }.onError {

            }
        }
    }

    fun getCollections() {
        if (collectionState is CollectionUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            getCollection.getAll()
                .onSuccess {
                    collectionState = CollectionUIState.Success(it.docs)
                }
                .onError {

                }
        }
    }
}