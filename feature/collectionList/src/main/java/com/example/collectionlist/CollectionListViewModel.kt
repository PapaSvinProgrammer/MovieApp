package com.example.collectionlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.collection.GetCollectionAll
import com.example.collection.GetCollectionByCategory
import com.example.ui.uiState.CollectionUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CollectionListViewModel @Inject constructor(
    private val getCollectionAll: GetCollectionAll,
    private val getCollectionByCategory: GetCollectionByCategory
): ViewModel() {
    private var page = 1

    var collectionState by mutableStateOf(CollectionUIState.Loading as CollectionUIState)
        private set

    fun getCollections(category: String?) {
        if (collectionState is CollectionUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = if (category == null)
                getCollectionAll.execute()
            else
                getCollectionByCategory.execute(category)

            res.onSuccess {
                collectionState = CollectionUIState.Success(it)
            }
        }
    }

    fun loadMoreCollections(category: String?) {
        page++

        viewModelScope.launch(Dispatchers.IO) {
            val res = if (category == null) {
                getCollectionAll.execute(page)
            }
            else {
                getCollectionByCategory.execute(
                    category = category,
                    page = page
                )
            }

            res.onSuccess {
                val temp = (collectionState as CollectionUIState.Success).data.toMutableList()
                temp.addAll(it)
                collectionState = CollectionUIState.Success(temp)
            }
        }
    }
}