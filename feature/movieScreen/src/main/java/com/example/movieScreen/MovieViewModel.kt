package com.example.movieScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.collectionusecase.GetCollectionBySlug
import com.example.comment.GetCommentByDate
import com.example.common.multiRequest
import com.example.model.person.PersonMovie
import com.example.ui.uiState.CollectionUIState
import com.example.ui.uiState.CommentUIState
import com.example.ui.uiState.ImageUIState
import com.example.ui.uiState.MovieUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val getMovieById: GetMovieById,
    private val getMovieImages: GetMovieImages,
    private val getCollectionBySlug: GetCollectionBySlug,
    private val getCommentByDate: GetCommentByDate
): ViewModel() {
    private val _movieState = MutableStateFlow(MovieUIState.Loading as MovieUIState)
    private val _imageState = MutableStateFlow(ImageUIState.Loading as ImageUIState)
    private val _collectionState = MutableStateFlow(CollectionUIState.Loading as CollectionUIState)
    private val _commentState = MutableStateFlow(CommentUIState.Loading as CommentUIState)
    val movieState: StateFlow<MovieUIState> = _movieState
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
        if (movieState.value is MovieUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            getCommentByDate.execute(
                movieId = movieId,
                sort = -1
            ).onSuccess {
                _commentState.value = CommentUIState.Success(it)
            }
        }
    }

    fun getMovie(id: Int) {
        if (movieState.value is MovieUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMovieById.execute(id)

            res.onSuccess {
                _movieState.value = MovieUIState.Success(listOf(it))
                filterActors(it.persons)
            }
        }
    }

    fun getImages(movieId: Int) {
        if (imagesState.value is ImageUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMovieImages.execute(movieId)

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
                _collectionState.value = CollectionUIState.Success(
                    temp.filter {
                        it.slug != "hd"
                    }
                )
            }
        }
    }

    fun updateSelectedFact(text: String) {
        _selectedFact.value = text
    }

    private fun filterActors(list: List<PersonMovie>) {
        viewModelScope.launch(Dispatchers.Default) {
            _actors.value = list.filter { it.enProfession == "actor" }
            _voiceActors.value = list.filter { it.enProfession == "voice_actor" }
            _supportPersonal.value = list.filter {
                it.enProfession != "actor" &&  it.enProfession != "voice_actor"
            }
        }
    }
}