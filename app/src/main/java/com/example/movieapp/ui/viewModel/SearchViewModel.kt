package com.example.movieapp.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecases.GetCollection
import com.example.core.domain.usecases.GetPerson
import com.example.movieapp.ui.screen.uiState.CollectionUIState
import com.example.movieapp.ui.screen.uiState.PersonUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val getCollection: GetCollection,
    private val getPerson: GetPerson
): ViewModel() {
    var query by mutableStateOf("")
        private set
    var isExpanded by mutableStateOf(false)
        private set
    var collectionsState by mutableStateOf(CollectionUIState.Loading as CollectionUIState)
        private set
    var popularPersonState by mutableStateOf(PersonUIState.Loading as PersonUIState)
        private set
    var personByAgeState by mutableStateOf(PersonUIState.Loading as PersonUIState)
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

    fun getPopularPersons() {
        if (popularPersonState is PersonUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getPerson.getPersonsByCountAwards()

            if (res.isNotEmpty()) {
                popularPersonState = PersonUIState.Success(res)
            }
        }
    }
}