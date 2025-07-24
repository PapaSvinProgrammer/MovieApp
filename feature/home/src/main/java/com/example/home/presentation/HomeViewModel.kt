package com.example.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.collectionusecase.GetCollectionAll
import com.example.home.domain.GetMoviesByCollection
import com.example.home.domain.GetMoviesByCompany
import com.example.home.domain.GetMoviesByGenre
import com.example.home.utils.NavigationUtils
import com.example.ui.uiState.CollectionUIState
import com.example.ui.uiState.MovieUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class HomeViewModel @Inject constructor(
    private val getMoviesByGenre: GetMoviesByGenre,
    private val getMoviesByCollection: GetMoviesByCollection,
    private val getCollectionAll: GetCollectionAll,
    private val getMoviesByCompany: GetMoviesByCompany
): ViewModel() {
    private val _movieDramaState = MutableStateFlow(MovieUIState.Loading as MovieUIState)
    private val _movieBoevikState = MutableStateFlow(MovieUIState.Loading as MovieUIState)
    private val _movieBest250State = MutableStateFlow(MovieUIState.Loading as MovieUIState)
    private val _movieBest501State = MutableStateFlow(MovieUIState.Loading as MovieUIState)
    private val _movieBest100State = MutableStateFlow(MovieUIState.Loading as MovieUIState)
    private val _movieHBOState = MutableStateFlow(MovieUIState.Loading as MovieUIState)
    private val _movieNetflixState = MutableStateFlow(MovieUIState.Loading as MovieUIState)
    private val _collectionState = MutableStateFlow(CollectionUIState.Loading as CollectionUIState)

    val movieDramaState: StateFlow<MovieUIState> = _movieDramaState
    val movieBoevikState: StateFlow<MovieUIState> = _movieBoevikState
    val movieBest250State: StateFlow<MovieUIState> = _movieBest250State
    val movieBest501State: StateFlow<MovieUIState> = _movieBest501State
    val movieBest100State: StateFlow<MovieUIState> = _movieBest100State
    val movieHBOState: StateFlow<MovieUIState> = _movieHBOState
    val movieNetflixState: StateFlow<MovieUIState> = _movieNetflixState
    val collectionState: StateFlow<CollectionUIState> = _collectionState

    fun getMoviesDrama() {
        if (movieDramaState.value is MovieUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMoviesByGenre.execute(NavigationUtils.DRAMA_GENRE)

            res.onSuccess {
                _movieDramaState.value = MovieUIState.Success(it)
            }
        }
    }

    fun getMoviesBoevik() {
        if (movieBoevikState.value is MovieUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMoviesByGenre.execute(NavigationUtils.BOEVIK_GENRE)

            res.onSuccess {
                _movieBoevikState.value = MovieUIState.Success(it)
            }
        }
    }

    fun getMoviesBest250() {
        if (movieBest250State.value is MovieUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMoviesByCollection.execute(NavigationUtils.LIST250)

            res.onSuccess {
               _movieBest250State.value = MovieUIState.Success(it)
            }
        }
    }

    fun getMoviesBest501() {
        if (movieBest501State.value is MovieUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMoviesByCollection.execute(NavigationUtils.LIST501)

            res.onSuccess {
                _movieBest501State.value = MovieUIState.Success(it)
            }
        }
    }

    fun getMoviesBest100() {
        if (movieBest100State.value is MovieUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMoviesByCollection.execute(NavigationUtils.LIST_SCIFI)

            res.onSuccess {
                _movieBest100State.value = MovieUIState.Success(it)
            }
        }
    }

    fun getMoviesHBO() {
        if (movieHBOState.value is MovieUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMoviesByCompany.execute(NavigationUtils.HBO)

            res.onSuccess {
                _movieHBOState.value = MovieUIState.Success(it)
            }
        }
    }

    fun getMoviesNetflix() {
        if (movieNetflixState.value is MovieUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMoviesByCompany.execute(NavigationUtils.NETFLIX)

            res.onSuccess {
                _movieNetflixState.value = MovieUIState.Success(it)
            }
        }
    }

    fun getCollections() {
        if (collectionState.value is CollectionUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            getCollectionAll.execute().onSuccess {
                _collectionState.value = CollectionUIState.Success(it)
            }
        }
    }
}