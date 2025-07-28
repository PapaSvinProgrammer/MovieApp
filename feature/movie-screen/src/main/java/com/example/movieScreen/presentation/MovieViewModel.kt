package com.example.movieScreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.collectionusecase.GetCollectionBySlug
import com.example.comment.GetCommentByDate
import com.example.comment.model.CommentParams
import com.example.utils.multiRequest
import com.example.model.person.PersonMovie
import com.example.movieScreen.GetMovieById
import com.example.movieScreen.GetMovieImages
import com.example.movieScreen.domain.FilterCollection
import com.example.movieScreen.domain.FilterPersonsLikeActors
import com.example.movieScreen.domain.FilterPersonsLikeSupport
import com.example.movieScreen.domain.FilterPersonsLikeVoiceActors
import com.example.movieScreen.model.MovieParams
import com.example.ui.uiState.CollectionUIState
import com.example.ui.uiState.CommentUIState
import com.example.ui.uiState.ImageUIState
import com.example.ui.uiState.MovieUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
): ViewModel() {
    private val _movieUIState = MutableStateFlow(MovieUIState.Loading as MovieUIState)
    private val _imageState = MutableStateFlow(ImageUIState.Loading as ImageUIState)
    private val _collectionState = MutableStateFlow(CollectionUIState.Loading as CollectionUIState)
    private val _commentState = MutableStateFlow(CommentUIState.Loading as CommentUIState)
    val movieUIState: StateFlow<MovieUIState> = _movieUIState
    val imagesState: StateFlow<ImageUIState> = _imageState
    val collectionState: StateFlow<CollectionUIState> = _collectionState
    val commentState: StateFlow<CommentUIState> = _commentState

    private val _actors = MutableStateFlow<List<PersonMovie>>(listOf())
    private val _voiceActors = MutableStateFlow<List<PersonMovie>>(listOf())
    private val _supportPersonal = MutableStateFlow<List<PersonMovie>>(listOf())
    val actors: StateFlow<List<PersonMovie>> = _actors
    val voiceActors: StateFlow<List<PersonMovie>> = _voiceActors
    val supportPersonal: StateFlow<List<PersonMovie>> = _supportPersonal

    private val _selectedFact = MutableStateFlow("")
    val selectedFact: StateFlow<String> = _selectedFact

    fun getComments(movieId: Int) {
        if (movieUIState.value is MovieUIState.Success) return

        val params = CommentParams(
            movieId = movieId,
            sort = -1
        )

        viewModelScope.launch(Dispatchers.IO) {
            getCommentByDate.execute(params).onSuccess {
                _commentState.value = CommentUIState.Success(it)
            }
        }
    }

    fun getMovie(id: Int) {
        if (movieUIState.value is MovieUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMovieById.execute(id)

            res.onSuccess {
                _movieUIState.value = MovieUIState.Success(listOf(it))
                filterActors(it.persons)
            }
        }
    }

    fun getImages(movieId: Int) {
        if (imagesState.value is ImageUIState.Success) return

        val params = MovieParams(
            movieId = movieId
        )

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMovieImages.execute(params)

            res.onSuccess {
                _imageState.value = ImageUIState.Success(it)
            }
        }
    }

    fun getCollections(list: List<String>) {
        if (collectionState.value is CollectionUIState.Success) return

        viewModelScope.launch(Dispatchers.Default) {
            val temp = multiRequest(list) {
                getCollectionBySlug.execute(it)
            }

            if (temp.isNotEmpty()) {
                val res = filterCollection.execute(temp)
                _collectionState.value = CollectionUIState.Success(res)
            }
        }
    }

    fun updateSelectedFact(text: String) {
        _selectedFact.value = text
    }

    private fun filterActors(list: List<PersonMovie>) {
        viewModelScope.launch(Dispatchers.Default) {
            _actors.value = filterPersonsLikeActors.execute(list)
            _voiceActors.value = filterPersonsLikeVoiceActors.execute(list)
            _supportPersonal.value = filterPersonsLikeSupport.execute(list)
        }
    }
}