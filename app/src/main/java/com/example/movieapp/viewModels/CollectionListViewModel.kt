package com.example.movieapp.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecases.GetCollection
import com.example.movieapp.ui.screen.uiState.CollectionUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CollectionListViewModel @Inject constructor(
    private val getCollection: GetCollection
): ViewModel() {
    private var page = 1

    var collectionState by mutableStateOf(CollectionUIState.Loading as CollectionUIState)
        private set

    fun getCollections(category: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = if (category == null)
                getCollection.getAll()
            else
                getCollection.getByCategory(category)

            if (res.isNotEmpty()) {
                collectionState = CollectionUIState.Success(res)
            }
        }
    }

    fun loadMoreCollections(category: String?) {
        page++

        viewModelScope.launch(Dispatchers.IO) {
            val res = if (category == null)
                getCollection.getAll(page)
            else
                getCollection.getByCategory(category = category, page = page)

            val temp = (collectionState as CollectionUIState.Success).data.toMutableList()
            temp.addAll(res)
            collectionState = CollectionUIState.Success(temp)
        }
    }
}