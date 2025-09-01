package com.mordva.movieScreen.presentation.movie

import androidx.lifecycle.ViewModel
import com.mordva.domain.usecase.collection.GetCollectionBySlug
import com.mordva.domain.usecase.comment.GetCommentByDate
import com.mordva.domain.usecase.comment.model.CommentParams
import com.mordva.domain.usecase.movie.GetMovieById
import com.mordva.domain.usecase.movie.GetMovieImages
import com.mordva.domain.usecase.movie.model.MovieParams
import com.mordva.model.movie.Movie
import com.mordva.model.person.PersonMovie
import com.mordva.movieScreen.domain.AddRatedMovie
import com.mordva.movieScreen.domain.FilterCollection
import com.mordva.movieScreen.domain.FilterPersonsLikeActors
import com.mordva.movieScreen.domain.FilterPersonsLikeSupport
import com.mordva.movieScreen.domain.FilterPersonsLikeVoiceActors
import com.mordva.movieScreen.domain.model.RatedMovieParams
import com.mordva.movieScreen.presentation.movie.widget.UIState
import com.mordva.ui.uiState.MovieUIState
import com.mordva.util.cancelAllJobs
import com.mordva.util.launchWithoutOld
import com.mordva.util.multiRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal class MovieViewModel @Inject constructor(
    private val getMovieById: GetMovieById,
    private val getMovieImages: GetMovieImages,
    private val getCollectionBySlug: GetCollectionBySlug,
    private val getCommentByDate: GetCommentByDate,
    private val filterCollection: FilterCollection,
    private val filterPersonsLikeVoiceActors: FilterPersonsLikeVoiceActors,
    private val filterPersonsLikeActors: FilterPersonsLikeActors,
    private val filterPersonsLikeSupport: FilterPersonsLikeSupport,
    private val addRatedMovie: AddRatedMovie
) : ViewModel() {
    private val _uiState = MutableStateFlow(UIState())
    val uiState = _uiState.asStateFlow()

    fun updateScoreSheetVisible(visible: Boolean) {
        _uiState.update {
            it.copy(scoreSheetVisible = visible)
        }
    }

    fun updateCollapsedState(state: Boolean) {
        _uiState.update {
            it.copy(isCollapsed = state)
        }
    }

    fun updateSelectedFact(text: String) {
        _uiState.update {
            it.copy(selectedFact = text)
        }
    }

    fun addRatedMovie(rating: Int) = launchWithoutOld(ADD_RATED_MOVIE_JOB) {
        val params = RatedMovieParams(
            movie = uiState.value.movieState.body() ?: Movie(),
            rating = rating
        )

        addRatedMovie.execute(params)
    }

    fun getComments(movieId: Int) = launchWithoutOld(GET_COMMENTS_JOB) {
        val params = CommentParams(
            movieId = movieId,
            sort = -1
        )

        getCommentByDate.execute(params).onSuccess { comments ->
            _uiState.update {
                it.copy(comments = comments)
            }
        }
    }

    fun getMovie(id: Int) = launchWithoutOld(GET_MOVIE_JOB) {
        val res = getMovieById.execute(id)

        res.onSuccess { movie ->
            val state = MovieUIState.Success(listOf(movie))

            _uiState.update {
                it.copy(movieState = state)
            }

            filterActors(movie.persons)
        }
    }

    fun getImages(movieId: Int) = launchWithoutOld(GET_IMAGES_JOB) {
        val params = MovieParams(
            movieId = movieId
        )

        val res = getMovieImages.execute(params)

        res.onSuccess { images ->
            _uiState.update {
                it.copy(images = images)
            }
        }
    }

    fun getCollections(list: List<String>) = launchWithoutOld(GET_COLLECTIONS) {
        if (uiState.value.collections.isNotEmpty()) {
            return@launchWithoutOld
        }

        val temp = multiRequest(list) {
            getCollectionBySlug.execute(it)
        }

        if (temp.isNotEmpty()) {
            val res = filterCollection.execute(temp)

            _uiState.update {
                it.copy(collections = res)
            }
        }
    }

    private fun filterActors(list: List<PersonMovie>) = launchWithoutOld(FILTER_ACTORS_JOB) {
        _uiState.update {
            it.copy(actors = filterPersonsLikeActors.execute(list))
        }

        _uiState.update {
            it.copy(voiceActors = filterPersonsLikeVoiceActors.execute(list))
        }

        _uiState.update {
            it.copy(supportPersonal = filterPersonsLikeSupport.execute(list))
        }
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    private companion object {
        const val ADD_RATED_MOVIE_JOB = "add_rated_movie"
        const val GET_COMMENTS_JOB = "get_comments"
        const val GET_MOVIE_JOB = "get_movie"
        const val GET_IMAGES_JOB = "get_images"
        const val GET_COLLECTIONS = "get_collections"
        const val FILTER_ACTORS_JOB = "filter_actors"
    }
}
