package com.example.movieapp.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecases.GetCollection
import com.example.core.domain.usecases.GetMovie
import com.example.movieapp.ui.uiState.CollectionUIState
import com.example.movieapp.ui.uiState.ImageUIState
import com.example.movieapp.ui.uiState.MovieUIState
import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.module.image.Collection
import com.example.network.module.person.PersonMovie
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val getMovie: GetMovie,
    private val getCollection: GetCollection
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
            val res = getMovie.getById(id)

            res.onSuccess {
                movieState = MovieUIState.Success(listOf(it))
                filterActors(it.persons)
            }.onError {

            }
        }
    }

    fun getImages(movieId: Int) {
        if (imagesState is ImageUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMovie.getImages(movieId)

            res.onSuccess {
                imagesState = ImageUIState.Success(it.docs)
            }
        }
    }

    fun getCollections(list: List<String>) {
        if (collectionState is CollectionUIState.Success) return

        viewModelScope.launch(Dispatchers.Default) {
            val tasks = mutableListOf<Deferred<Operation<Collection, NetworkError>>>()

            list.forEach {
                val task = async(Dispatchers.IO) { getCollection.getBySlug(it) }
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