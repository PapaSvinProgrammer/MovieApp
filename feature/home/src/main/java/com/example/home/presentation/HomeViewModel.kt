package com.example.home.presentation

import androidx.lifecycle.ViewModel
import com.example.collectionusecase.GetCollectionAll
import com.example.home.domain.GetMoviesByCollection
import com.example.home.domain.GetMoviesByCompany
import com.example.home.domain.GetMoviesByGenre
import com.example.home.presentation.widget.UIState
import com.example.home.utils.NavigationUtils
import com.example.movieScreen.model.MovieParams
import com.example.ui.uiState.CollectionUIState
import com.example.ui.uiState.MovieUIState
import com.example.utils.cancelAllJobs
import com.example.utils.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal class HomeViewModel @Inject constructor(
    private val getMoviesByGenre: GetMoviesByGenre,
    private val getMoviesByCollection: GetMoviesByCollection,
    private val getCollectionAll: GetCollectionAll,
    private val getMoviesByCompany: GetMoviesByCompany
) : ViewModel() {
    private val _uiState = MutableStateFlow(UIState())
    val uiState = _uiState.asStateFlow()

    fun getMoviesDrama() = launchWithoutOld(GET_DRAMA_JOB) {
        if (uiState.value.movieDramaState is MovieUIState.Success) return@launchWithoutOld

        val params = MovieParams(genre = NavigationUtils.DRAMA_GENRE)
        val res = getMoviesByGenre.execute(params)

        res.onSuccess { movies ->
            _uiState.update {
                it.copy(movieDramaState = MovieUIState.Success(movies))
            }
        }
    }

    fun getMoviesBoevik() = launchWithoutOld(GET_BOEVIK_JOB) {
        if (uiState.value.movieBoevikState is MovieUIState.Success) return@launchWithoutOld

        val params = MovieParams(genre = NavigationUtils.BOEVIK_GENRE)
        val res = getMoviesByGenre.execute(params)

        res.onSuccess { movies ->
            _uiState.update {
                it.copy(movieBoevikState = MovieUIState.Success(movies))
            }
        }
    }

    fun getMoviesBest250() = launchWithoutOld(GET_BEST250_JOB) {
        if (uiState.value.movieBest250State is MovieUIState.Success) return@launchWithoutOld

        val params = MovieParams(name = NavigationUtils.LIST250)
        val res = getMoviesByCollection.execute(params)

        res.onSuccess { movies ->
            _uiState.update {
                it.copy(movieBest250State = MovieUIState.Success(movies))
            }
        }
    }

    fun getMoviesBest501() = launchWithoutOld(GET_BEST501_JOB) {
        if (uiState.value.movieBest501State is MovieUIState.Success) return@launchWithoutOld

        val params = MovieParams(name = NavigationUtils.LIST501)
        val res = getMoviesByCollection.execute(params)

        res.onSuccess { movies ->
            _uiState.update {
                it.copy(movieBest501State = MovieUIState.Success(movies))
            }
        }
    }

    fun getMoviesBest100() = launchWithoutOld(GET_BEST100_JOB) {
        if (uiState.value.movieBest100State is MovieUIState.Success) return@launchWithoutOld

        val params = MovieParams(name = NavigationUtils.LIST_SCIFI)
        val res = getMoviesByCollection.execute(params)

        res.onSuccess { movies ->
            _uiState.update {
                it.copy(movieBest100State = MovieUIState.Success(movies))
            }
        }
    }

    fun getMoviesHBO() = launchWithoutOld(GET_HBO_JOB) {
        if (uiState.value.movieHBOState is MovieUIState.Success) return@launchWithoutOld

        val params = MovieParams(name = NavigationUtils.HBO)
        val res = getMoviesByCompany.execute(params)

        res.onSuccess { movies ->
            _uiState.update {
                it.copy(movieHBOState = MovieUIState.Success(movies))
            }
        }
    }

    fun getMoviesNetflix() = launchWithoutOld(GET_NETFLIX_JOB) {
        if (uiState.value.movieNetflixState is MovieUIState.Success) return@launchWithoutOld

        val params = MovieParams(name = NavigationUtils.NETFLIX)
        val res = getMoviesByCompany.execute(params)

        res.onSuccess { movies ->
            _uiState.update {
                it.copy(movieNetflixState = MovieUIState.Success(movies))
            }
        }
    }

    fun getCollections() = launchWithoutOld(GET_COLLECTIONS_JOB) {

        if (uiState.value.collectionState is CollectionUIState.Success) return@launchWithoutOld

        getCollectionAll.execute(1).onSuccess { collections ->
            _uiState.update {
                it.copy(collectionState = CollectionUIState.Success(collections))
            }
        }
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    private companion object {
        const val GET_DRAMA_JOB = "get_movies_drama"
        const val GET_BOEVIK_JOB = "get_movies_boevik"
        const val GET_BEST250_JOB = "get_movies_best250"
        const val GET_BEST501_JOB = "get_movies_best501"
        const val GET_BEST100_JOB = "get_movies_best100"
        const val GET_HBO_JOB = "get_movies_hbo"
        const val GET_NETFLIX_JOB = "get_movies_netflix"
        const val GET_COLLECTIONS_JOB = "get_collections"
    }
}
