package com.example.collectionlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.collectionusecase.GetCollectionAll
import com.example.collectionusecase.GetCollectionByCategory
import com.example.collectionusecase.model.CollectionParams
import com.example.ui.uiState.CollectionUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class CollectionListViewModel @Inject constructor(
    private val getCollectionAll: GetCollectionAll,
    private val getCollectionByCategory: GetCollectionByCategory
): ViewModel() {
    private var page = 1

    private val _collectionState = MutableStateFlow(CollectionUIState.Loading as CollectionUIState)
    val collectionState: StateFlow<CollectionUIState> = _collectionState

    fun getCollections(category: String?) {
        if (collectionState.value is CollectionUIState.Success) return

        val params = CollectionParams(
            page = page,
            category = category ?: ""
        )

        viewModelScope.launch(Dispatchers.IO) {
            val res = if (category == null)
                getCollectionAll.execute(page)
            else
                getCollectionByCategory.execute(params)

            res.onSuccess {
                _collectionState.value = CollectionUIState.Success(it)
            }
        }
    }

    fun loadMoreCollections(category: String?) {
        page++

        val params = CollectionParams(
            category = category ?: "",
            page = page
        )

        viewModelScope.launch(Dispatchers.IO) {
            val res = if (category == null) {
                getCollectionAll.execute(page)
            }
            else {
                getCollectionByCategory.execute(params)
            }

            res.onSuccess {
                val temp = (collectionState.value as CollectionUIState.Success)
                    .data
                    .toMutableList()

                temp.addAll(it)
                _collectionState.value = CollectionUIState.Success(temp)
            }
        }
    }
}
