package com.example.movieScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.collection.GetCollectionBySlug
import com.example.model.image.Collection
import com.example.model.person.PersonMovie
import com.example.ui.uiState.CollectionUIState
import com.example.ui.uiState.ImageUIState
import com.example.ui.uiState.MovieUIState
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val getMovieById: GetMovieById,
    private val getMovieImages: GetMovieImages,
    private val getCollectionBySlug: GetCollectionBySlug
): ViewModel() {
    var movieState by mutableStateOf(MovieUIState.Loading as MovieUIState)
        private set
    var imagesState by mutableStateOf(ImageUIState.Loading as ImageUIState)
        private set
    var collectionState by mutableStateOf(CollectionUIState.Loading as CollectionUIState)
        private set

    var actors by mutableStateOf<List<PersonMovie>>(listOf())
        private set
    var voiceActors by mutableStateOf<List<PersonMovie>>(listOf())
        private set
    var supportPersonal by mutableStateOf<List<PersonMovie>>(listOf())
        private set

    fun getMovie(id: Int) {
        if (movieState is MovieUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMovieById.execute(id)

            res.onSuccess {
                movieState = MovieUIState.Success(listOf(it))
                filterActors(it.persons)
            }
        }
    }

    fun getImages(movieId: Int) {
        if (imagesState is ImageUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMovieImages.execute(movieId)

            res.onSuccess {
                imagesState = ImageUIState.Success(it)
            }
        }
    }

    fun getCollections(list: List<String>) {
        if (collectionState is CollectionUIState.Success) return

        viewModelScope.launch(Dispatchers.Default) {
            val tasks = mutableListOf<Deferred<Result<Collection>>>()

            list.forEach {
                val task = async(Dispatchers.IO) {
                    getCollectionBySlug.execute(it)
                }

                tasks.add(task)
            }

            val temp = mutableListOf<Collection>()

            tasks.awaitAll().forEach {
                it.onSuccess { data ->
                    if (data.slug != "hd") temp.add(data)
                }
            }

            if (temp.isNotEmpty()) {
                collectionState = CollectionUIState.Success(temp)
            }
        }
    }

    private fun filterActors(list: List<PersonMovie>) {
        viewModelScope.launch(Dispatchers.Default) {
            actors = list.filter { it.enProfession == "actor" }
            voiceActors = list.filter { it.enProfession == "voice_actor" }
            supportPersonal = list.filter {
                it.enProfession != "actor" &&  it.enProfession != "voice_actor"
            }
        }
    }
}