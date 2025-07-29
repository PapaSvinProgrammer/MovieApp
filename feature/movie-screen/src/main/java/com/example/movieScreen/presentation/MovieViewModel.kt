package com.example.movieScreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.collectionusecase.GetCollectionBySlug
import com.example.comment.GetCommentByDate
import com.example.comment.model.CommentParams
import com.example.model.person.PersonMovie
import com.example.movieScreen.GetMovieById
import com.example.movieScreen.GetMovieImages
import com.example.movieScreen.domain.FilterCollection
import com.example.movieScreen.domain.FilterPersonsLikeActors
import com.example.movieScreen.domain.FilterPersonsLikeSupport
import com.example.movieScreen.domain.FilterPersonsLikeVoiceActors
import com.example.movieScreen.model.MovieParams
import com.example.movieScreen.presentation.widget.UIState
import com.example.ui.uiState.MovieUIState
import com.example.utils.cancelAllJobs
import com.example.utils.launchWithoutOld
import com.example.utils.multiRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class MovieViewModel @Inject constructor(
    private val getMovieById: GetMovieById,
    private val getMovieImages: GetMovieImages,
    private val getCollectionBySlug: GetCollectionBySlug,
    private val getCommentByDate: GetCommentByDate,
    private val filterCollection: FilterCollection,
    private val filterPersonsLikeVoiceActors: FilterPersonsLikeVoiceActors,
    private val filterPersonsLikeActors: FilterPersonsLikeActors,
    private val filterPersonsLikeSupport: FilterPersonsLikeSupport
) : ViewModel() {
    private val _uiState = MutableStateFlow(UIState())
    val uiState = _uiState.asStateFlow()

    fun updateSelectedFact(text: String) {
        _uiState.update {
            it.copy(selectedFact = text)
        }
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

    private fun filterActors(list: List<PersonMovie>) {
        viewModelScope.launch(Dispatchers.Default) {
            _uiState.update {
                it.copy(actors = filterPersonsLikeActors.execute(list))
                it.copy(voiceActors = filterPersonsLikeVoiceActors.execute(list))
                it.copy(supportPersonal = filterPersonsLikeSupport.execute(list))
            }
        }
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    private companion object {
        const val GET_COMMENTS_JOB = "get_comments"
        const val GET_MOVIE_JOB = "get_movie"
        const val GET_IMAGES_JOB = "get_images"
        const val GET_COLLECTIONS = "get_collections"
    }
}
