package com.example.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.collectionusecase.GetCollectionAll
import com.example.home.domain.GetMoviesByCollection
import com.example.home.domain.GetMoviesByCompany
import com.example.home.domain.GetMoviesByGenre
import com.example.ui.uiState.CollectionUIState
import com.example.ui.uiState.MovieUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getMoviesByGenre: GetMoviesByGenre,
    private val getMoviesByCollection: GetMoviesByCollection,
    //private val getCollectionAll: GetCollectionAll,
    private val getMoviesByCompany: GetMoviesByCompany
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
            val res = getMoviesByGenre.execute("драма")

            res.onSuccess {
                movieDramaState = MovieUIState.Success(it)
            }
        }
    }

    fun getMoviesBoevik() {
        if (movieBoevikState is MovieUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMoviesByGenre.execute("боевик")

            res.onSuccess {
                movieBoevikState = MovieUIState.Success(it)
            }
        }
    }

    fun getMoviesBest250() {
        if (movieBest250State is MovieUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMoviesByCollection.execute("top250")

            res.onSuccess {
                movieBest250State = MovieUIState.Success(it)
            }
        }
    }

    fun getMoviesBest501() {
        if (movieBest501State is MovieUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMoviesByCollection.execute("best_501")

            res.onSuccess {
                movieBest501State = MovieUIState.Success(it)
            }
        }
    }

    fun getMoviesBest100() {
        if (movieBest100State is MovieUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMoviesByCollection.execute("top_100_scifi_by_total_scifi_online")

            res.onSuccess {
                movieBest100State = MovieUIState.Success(it)
            }
        }
    }

    fun getMoviesHBO() {
        if (movieHBOState is MovieUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMoviesByCompany.execute("HBO")

            res.onSuccess {
                movieHBOState = MovieUIState.Success(it)
            }
        }
    }

    fun getMoviesNetflix() {
        if (movieNetflixState is MovieUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMoviesByCompany.execute("Netflix")

            res.onSuccess {
                movieNetflixState = MovieUIState.Success(it)
            }
        }
    }

    fun getCollections() {
        if (collectionState is CollectionUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
//            getCollectionAll.execute().onSuccess {
//                    collectionState = CollectionUIState.Success(it)
//                }
        }
    }
}