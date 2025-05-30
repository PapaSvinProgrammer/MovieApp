package com.example.movieapp.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecases.GetCollection
import com.example.core.domain.usecases.GetMovie
import com.example.core.domain.usecases.GetPerson
import com.example.movieapp.ui.screen.uiState.CollectionUIState
import com.example.movieapp.ui.screen.uiState.MovieUIState
import com.example.movieapp.ui.screen.uiState.PersonUIState
import com.example.network.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val getCollection: GetCollection,
    private val getPerson: GetPerson,
    private val getMovie: GetMovie
): ViewModel() {
    var query by mutableStateOf("")
        private set
    var isExpanded by mutableStateOf(false)
        private set
    var collectionsState by mutableStateOf(CollectionUIState.Loading as CollectionUIState)
        private set
    var dubbingPersonState by mutableStateOf(PersonUIState.Loading as PersonUIState)
        private set
    var topSerialsState by mutableStateOf(MovieUIState.Loading as MovieUIState)
        private set

    fun updateQuery(text: String) {
        query = text
    }

    fun updateExpanded(state: Boolean) {
        isExpanded = state
    }

    fun getCollections() {
        if (collectionsState is CollectionUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getCollection.getByCategory("Фильмы")

            if (res.isNotEmpty()) {
                collectionsState = CollectionUIState.Success(res)
            }
        }
    }

    fun getPopularDubbingActor() {
        if (dubbingPersonState is PersonUIState.Success) return

        val queryParameters = mapOf(
            Constants.PROFESSION_VALUE to "Актер дубляжа"
        )

        viewModelScope.launch(Dispatchers.IO) {
            val res = getPerson.getPersonsByFilter(queryParameters)

            if (res.isNotEmpty()) {
                dubbingPersonState = PersonUIState.Success(res)
            }
        }
    }

    fun getTopSerials() {
        if (topSerialsState is MovieUIState.Success) return

        val queryParameters = mapOf(
            "isSeries" to "true",
            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
            Constants.SORT_TYPE to Constants.SORT_DESC
        )

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMovie.getByFilter(queryParameters)

            if (res.isNotEmpty()) {
                topSerialsState = MovieUIState.Success(res)
            }
        }
    }
}