package com.mordva.base_view_models.movie_list

import androidx.lifecycle.ViewModel
import com.mordva.domain.usecase.movie.GetMovieByFilter
import com.mordva.ui.uiState.MovieUIState
import com.mordva.util.Constants
import com.mordva.util.cancelAllJobs
import com.mordva.util.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val getMovieByFilter: GetMovieByFilter
) : ViewModel() {
    private var page = 1

    private val _movieUIState = MutableStateFlow(MovieUIState.Loading as MovieUIState)
    val moviesState: StateFlow<MovieUIState> = _movieUIState

    fun getMovies(queryParameters: List<Pair<String, String>>) = launchWithoutOld(GET_MOVIES_JOB) {
        if (moviesState.value is MovieUIState.Success) return@launchWithoutOld

        val res = getMovieByFilter.execute(queryParameters)

        res.onSuccess {
            _movieUIState.value = MovieUIState.Success(it)
        }
    }

    fun loadMoreMovies(
        queryParameters: List<Pair<String, String>>
    ) = launchWithoutOld(LOAD_MOVIES_JOB) {
        page++

        val query = queryParameters.toMutableList()
        query.add(Constants.PAGE_FIELD to page.toString())

        val res = getMovieByFilter.execute(query)

        res.onSuccess {
            val temp = (moviesState.value as MovieUIState.Success)
                .data
                .toMutableList()
            temp.addAll(it)
            _movieUIState.value = MovieUIState.Success(temp)
        }
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    private companion object {
        const val GET_MOVIES_JOB = "get_movies"
        const val LOAD_MOVIES_JOB = "load_more_movies"
    }
}